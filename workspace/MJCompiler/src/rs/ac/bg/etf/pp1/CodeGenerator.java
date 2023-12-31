package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.ANDStart;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.CondFactExpr;
import rs.ac.bg.etf.pp1.ast.CondFactRelop;
import rs.ac.bg.etf.pp1.ast.CondTermOne;
import rs.ac.bg.etf.pp1.ast.DesigExprDouble;
import rs.ac.bg.etf.pp1.ast.DesigExprOne;
import rs.ac.bg.etf.pp1.ast.DesigIdent;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAsExpr;
import rs.ac.bg.etf.pp1.ast.DesignatorFuncCall;
import rs.ac.bg.etf.pp1.ast.DesignatorMinusMinus;
import rs.ac.bg.etf.pp1.ast.DesignatorPlusPlus;
import rs.ac.bg.etf.pp1.ast.DsignatorAss;
import rs.ac.bg.etf.pp1.ast.ELSEStart;
import rs.ac.bg.etf.pp1.ast.EQGREStart;
import rs.ac.bg.etf.pp1.ast.ExprAddop;
import rs.ac.bg.etf.pp1.ast.ExprMinus;
import rs.ac.bg.etf.pp1.ast.ExprNoMinus;
import rs.ac.bg.etf.pp1.ast.FactorBoolConst;
import rs.ac.bg.etf.pp1.ast.FactorCharConst;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorExprExpr;
import rs.ac.bg.etf.pp1.ast.FactorFuncCall;
import rs.ac.bg.etf.pp1.ast.FactorNewBracket;
import rs.ac.bg.etf.pp1.ast.FactorNumConst;
import rs.ac.bg.etf.pp1.ast.IfCond;
import rs.ac.bg.etf.pp1.ast.IfNoElse;
import rs.ac.bg.etf.pp1.ast.Label;
import rs.ac.bg.etf.pp1.ast.LabelIdent;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodDeclReturn;
import rs.ac.bg.etf.pp1.ast.MethodDeclType;
import rs.ac.bg.etf.pp1.ast.MulopMod;
import rs.ac.bg.etf.pp1.ast.MulopMulti;
import rs.ac.bg.etf.pp1.ast.NoDesig;
import rs.ac.bg.etf.pp1.ast.ORStart;
import rs.ac.bg.etf.pp1.ast.RPARENStart;
import rs.ac.bg.etf.pp1.ast.RelopEqual;
import rs.ac.bg.etf.pp1.ast.RelopGeEq;
import rs.ac.bg.etf.pp1.ast.RelopGre;
import rs.ac.bg.etf.pp1.ast.RelopLess;
import rs.ac.bg.etf.pp1.ast.RelopLessEq;
import rs.ac.bg.etf.pp1.ast.RelopNoEqual;
import rs.ac.bg.etf.pp1.ast.StatementBreak;
import rs.ac.bg.etf.pp1.ast.StatementContinue;
import rs.ac.bg.etf.pp1.ast.StatementExpr;
import rs.ac.bg.etf.pp1.ast.StatementIf;
import rs.ac.bg.etf.pp1.ast.StatementIfElse;
import rs.ac.bg.etf.pp1.ast.StatementMap;
import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.StatementRead;
import rs.ac.bg.etf.pp1.ast.StatementRetExp;
import rs.ac.bg.etf.pp1.ast.StatementReturn;
import rs.ac.bg.etf.pp1.ast.StatementWhile;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermFactor;
import rs.ac.bg.etf.pp1.ast.TermFactorMulop;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.WHILEStart;
import rs.ac.bg.etf.pp1.ast.WhileCondition;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.ast.IFStart;





public class CodeGenerator extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());


	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}


	private int mainPc=0;
	private Stack<Integer> nijeIspunjenFact = new Stack<>();
	private Stack<Integer> ispunjenFact = new Stack<>();
	private Stack<List<Integer>> elseFact = new Stack<>();
	private Stack<Integer> returnadr=new Stack<>();
	private String operacije;
	
	private Stack<Integer> nijeIspunjenWhile = new Stack<>();
	private Stack<Integer> whilereturn=new Stack<>();
	
	public int getMainPc(){
		return mainPc;
	}
	
	public CodeGenerator() {
		int vr=1;
		//Chr
		Obj dodaj;
		dodaj = Tab.find("chr");
		dodaj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(vr);
		Code.put(dodaj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		//Ord
		Obj dodaj1;
		dodaj1 = Tab.find("ord");
		dodaj1.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(vr);
		Code.put(dodaj1.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		//Len
		Obj dodaj2;
		dodaj2 = Tab.find("len");
		dodaj2.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(vr);
		Code.put(dodaj2.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);	
	}
	
	@Override
	public void visit(MethodDeclReturn MethodDeclReturn) {
		if("main".equals(MethodDeclReturn.getNameFuncT())) {
			mainPc=Code.pc;
		}
		MethodDeclReturn.obj.setAdr(Code.pc);
		Obj meth=MethodDeclReturn.obj;
		int cntarg=meth.getLevel();
		int cntparam=meth.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(cntarg);
		Code.put(cntparam);
	}
	
	@Override
	public void visit(MethodDeclType MethodDeclType) {
		if(MethodDeclType.getMethodDeclReturn().obj.getType()==Tip.voidType()) {
			Code.put(Code.exit);
			Code.put(Code.return_);
			return;
		} 
			Code.put(Code.trap);
			Code.put(-1);
	}
	
	@Override
	public void visit(StatementReturn StatementReturn) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(StatementRetExp StatementRetExp) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(StatementRead StatementRead) {
		if(StatementRead.getDesignator().obj.getType()==Tab.charType)Code.put(Code.bread);
		else Code.put(Code.read);
		if(StatementRead.getDesignator().obj.getKind()==Obj.Fld) {
			if(StatementRead.getDesignator().obj.getType()==Tab.charType)Code.put(Code.bastore);
			else Code.put(Code.astore);
		}
		else 
			Code.store(StatementRead.getDesignator().obj);
	}
	
	@Override
	public void visit(StatementExpr StatementExpr) {
		if(StatementExpr.getExpr().struct==Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(StatementPrint StatementPrint) {
		if(StatementPrint.getExpr().struct==Tab.charType) {
			Code.loadConst(StatementPrint.getN2());
			Code.put(Code.bprint);
		}
		else {
			Code.loadConst(StatementPrint.getN2());
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(FactorDesignator FactorDesignator) {
		if(FactorDesignator.getDesignator().obj.getKind()!=Obj.Fld)
			Code.load(FactorDesignator.getDesignator().obj);
		else {
			//empty
		}
	}
	
	@Override
	public void visit(FactorNumConst FactorNumConst) {
		Obj konst=Tab.insert(Obj.Con, "#konstanta", Tab.intType);
		konst.setAdr(FactorNumConst.getN1());
		konst.setLevel(0);
		Code.load(konst);
	}
	
	@Override
	public void visit(FactorCharConst FactorCharConst) {
		Obj konst=Tab.insert(Obj.Con, "#konstanta", Tab.charType);
		konst.setAdr(FactorCharConst.getC1());
		konst.setLevel(0);
		Code.load(konst);
	}
	
	@Override
	public void visit(FactorBoolConst FactorBoolConst) {
		Obj konst=Tab.insert(Obj.Con, "#konstanta", Tip.boolType());
		if(FactorBoolConst.getB1())
			konst.setAdr(1);
		else if(!FactorBoolConst.getB1())
			konst.setAdr(0);
		konst.setLevel(0);
		Code.load(konst);
	}
	
	@Override
	public void visit(ExprAddop ExprAddop) {
		if(ExprAddop.getAddop() instanceof AddopPlus) {
			Code.put(Code.add);
			return;
		}
		Code.put(Code.sub);
	}
	
	@Override
	public void visit(ExprMinus ExprMinus) {
		Code.put(Code.neg);
	}
	
	@Override
	public void visit(TermFactorMulop TermFactorMulop) {
		if(TermFactorMulop.getMulop() instanceof MulopMulti) {
			Code.put(Code.mul);
			return;
		}
		if(TermFactorMulop.getMulop() instanceof MulopMod) {
			Code.put(Code.rem);
			return;
		}
		Code.put(Code.div);
		
	}
	
	@Override
	public void visit(DsignatorAss DsignatorAss) {
		if(DsignatorAss.getDesignator().obj.getKind()!=Obj.Fld)
		Code.store(DsignatorAss.getDesignator().obj);
		else Code.put(Code.astore);
	}
	
	@Override
	public void visit(DesigIdent DesigIdent) {
		if(DesigIdent.getParent() instanceof DesigExprOne) {
			Code.load(DesigIdent.obj);
		}
		if(DesigIdent.getParent() instanceof DesigExprDouble) {
			Code.load(DesigIdent.obj);
		}
	}
	
	
	
	@Override
	public void visit(DesigExprDouble DesigExprDouble) {
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		if(DesigExprDouble.obj.getType()==Tab.charType)
			Code.put(Code.baload);
		else	
			Code.put(Code.aload);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		
		if(!(DesigExprDouble.getParent() instanceof StatementRead)&& (!(DesigExprDouble.getParent() instanceof DsignatorAss)) && 
				!(DesigExprDouble.getParent() instanceof DesignatorPlusPlus) && !(DesigExprDouble.getParent() instanceof DesignatorMinusMinus))
		{
			if(DesigExprDouble.obj.getType()==Tab.charType)
				Code.put(Code.baload);
			else	
				Code.put(Code.aload);
		}
			
	}
	
	@Override
	public void visit(DesignatorPlusPlus DesignatorPlusPlus) {
		if(DesignatorPlusPlus.getDesignator().obj.getKind()==Obj.Var) {
			Code.load(DesignatorPlusPlus.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(DesignatorPlusPlus.getDesignator().obj);
		}
		else if(DesignatorPlusPlus.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(DesignatorPlusPlus.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(DesignatorPlusPlus.getDesignator().obj);
		}
		else if(DesignatorPlusPlus.getDesignator().obj.getKind()==Obj.Fld) {
			Code.put(Code.dup2);
			if(DesignatorPlusPlus.getDesignator().obj.getType()==Tab.charType)
				Code.put(Code.baload);
			else
				Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.add);
			if(DesignatorPlusPlus.getDesignator().obj.getType()==Tab.charType)
				Code.put(Code.bastore);
			else
				Code.put(Code.astore);
		}
	}
	
	@Override
	public void visit(DesignatorMinusMinus DesignatorMinusMinus) {
		if(DesignatorMinusMinus.getDesignator().obj.getKind()==Obj.Var) {
			Code.load(DesignatorMinusMinus.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(DesignatorMinusMinus.getDesignator().obj);
		}
		else if(DesignatorMinusMinus.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(DesignatorMinusMinus.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(DesignatorMinusMinus.getDesignator().obj);
		}
		else if(DesignatorMinusMinus.getDesignator().obj.getKind()==Obj.Fld) {
			Code.put(Code.dup2);
			if(DesignatorMinusMinus.getDesignator().obj.getType()==Tab.charType)
				Code.put(Code.baload);
			else
				Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.sub);
			if(DesignatorMinusMinus.getDesignator().obj.getType()==Tab.charType)
				Code.put(Code.bastore);
			else
				Code.put(Code.astore);
		}
	}
	
	@Override
	public void visit(FactorNewBracket FactorNewBracket) {
		if(FactorNewBracket.struct.getElemType()==Tab.charType) {
			Code.put(Code.newarray);
			Code.put(0);
		}
		else  {
			Code.put(Code.newarray);
			Code.put(1);
		}
	}
	
	@Override
	public void visit(FactorFuncCall FactorFuncCall) {
		Obj p=FactorFuncCall.getFactorFunc().obj;
		int pom=p.getAdr()-Code.pc;
		Code.put(Code.call);
		Code.put2(pom);
	}
	
	@Override
	public void visit(DesignatorFuncCall DesignatorFuncCall) {
		Obj p=DesignatorFuncCall.getFactorFunc().obj;
		int pom=p.getAdr()-Code.pc;
		Code.put(Code.call);
		Code.put2(pom);
		if(DesignatorFuncCall.getFactorFunc().obj.getType()!=Tip.voidType()) {
			Code.put(Code.pop);
		}
	}
	
	@Override
	public void visit(CondFactRelop CondFactRelop) {
		if(CondFactRelop.getRelop() instanceof RelopEqual) operacije="==";
		else if (CondFactRelop.getRelop() instanceof RelopNoEqual) operacije="!=";
		else if (CondFactRelop.getRelop() instanceof RelopGre) operacije=">";
		else if (CondFactRelop.getRelop() instanceof RelopGeEq) operacije=">=";
		else if (CondFactRelop.getRelop() instanceof RelopLess) operacije="<";
		else if (CondFactRelop.getRelop() instanceof RelopLessEq) operacije="<=";
	}
	
	private int pomocna(String op) {
		if(op.equals("==")) return Code.eq;
		if(op.equals("!=")) return Code.ne;
		if(op.equals(">")) return Code.gt;
		if(op.equals(">=")) return Code.ge;
		if(op.equals("<")) return Code.lt;
		return Code.le;
	}
	
	@Override
	public void visit(IFStart IFStart) {
		elseFact.push(new ArrayList<>());
	}
	
	@Override
	public void visit(ANDStart ANDStart) {
		Code.putFalseJump(pomocna(operacije), 0);
		nijeIspunjenFact.push(Code.pc-2);
	}
	
	@Override
	public void visit(CondFactExpr CondFactExpr) {
		Code.loadConst(1);
		operacije="==";
	}
	
	@Override
	public void visit(ORStart ORStart) {
		Code.putFalseJump(Code.inverse[pomocna(operacije)], 0);
		ispunjenFact.push(Code.pc-2);
		while(!nijeIspunjenFact.empty()) {
			int isp=nijeIspunjenFact.pop();
			Code.fixup(isp);
		}
	}
	
	@Override
	public void visit(IfCond IfCond) {
		Code.putFalseJump(pomocna(operacije), 0);
		elseFact.peek().add(Code.pc-2);
		while(!ispunjenFact.empty()) {
			int isp=ispunjenFact.pop();
			Code.fixup(isp);
		}
		
	}
	
	@Override
	public void visit(StatementIf StatementIf) {
		int i=0;
		while(i<elseFact.peek().size()) {
			int isp=elseFact.peek().get(i++);
			Code.fixup(isp);
		}
		elseFact.pop();
	}
	
	@Override
	public void visit(ELSEStart ELSEStart) {
		Code.putJump(0);
		returnadr.add(Code.pc-2);
		int i=0;
		while(i<elseFact.peek().size()) {
			int isp=elseFact.peek().get(i++);
			Code.fixup(isp);
		}
		while(!nijeIspunjenFact.empty()) {
			int isp=nijeIspunjenFact.pop();
			Code.fixup(isp);
		}
		elseFact.pop();
	}
	
	@Override
	public void visit(IfNoElse IfNoElse) {
		while(!nijeIspunjenFact.empty()) {
			int isp=nijeIspunjenFact.pop();
			Code.fixup(isp);
		}
	}
	
	@Override
	public void visit(StatementIfElse StatementIfElse) {
		int pov=returnadr.pop();
		Code.fixup(pov);
	}
	
	@Override
	public void visit(WhileCondition WhileCondition) {
		Code.putFalseJump(pomocna(operacije), 0);
		elseFact.peek().add(Code.pc-2);
		while(!ispunjenFact.empty()) {
			int isp=ispunjenFact.pop();
			Code.fixup(isp);
		}
	}
	
	@Override
	public void visit(StatementContinue StatementContinue) {
		Code.putJump(whilereturn.peek());
	}
	
	@Override
	public void visit(WHILEStart WHILEStart) {
		elseFact.push(new ArrayList<>());
		whilereturn.push(Code.pc);
	}
	
	@Override
	public void visit(StatementBreak StatementBreak) {
		Code.putJump(0);
		elseFact.peek().add(Code.pc-2);
	}
	
	@Override
	public void visit(StatementWhile StatementWhile) {
		Code.putJump(whilereturn.pop());
		int i=0;
		while(i<elseFact.peek().size()) {
			int isp=elseFact.peek().get(i++);
			Code.fixup(isp);
		}
		elseFact.pop();
	}
	
	@Override
	public void visit(FactorExprExpr FactorExprExpr) {
		
		TermFactor p1=(TermFactor)FactorExprExpr.getParent();
		ExprNoMinus p2=(ExprNoMinus)p1.getParent();
		DsignatorAss p3=(DsignatorAss)p2.getParent();
		Obj p=p3.getDesignator().obj;
		
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup);
		Code.put(Code.newarray);
		Code.put(1);
		Code.store(p);
		int skokpov=Code.pc;
		Code.put(Code.dup);
		
		Code.loadConst(0);
		Code.putFalseJump(Code.gt, 0);
		int razresi=Code.pc-2;
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup);
		Code.put(Code.newarray);
		if(FactorExprExpr.getType().struct==Tab.charType) Code.put(0);
		else Code.put(1);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup2);
		Code.load(p);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.astore);
		Code.put(Code.pop);
		Code.putJump(skokpov);
		Code.fixup(razresi);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.load(p);
	}
	int ispravna=0;
	int prepravi=0;
	
	@Override
	public void visit(EQGREStart EQGREStart) {
		StatementMap sm=(StatementMap) EQGREStart.getParent();
		
			Obj niz= sm.getDesignator1().obj;
			Obj niz2= sm.getDesignator().obj;
			Code.load(niz);
			Code.put(Code.arraylength);
			Code.put(Code.dup);
			Code.put(Code.newarray);
			if(sm.getDesignator1().obj.getType().getElemType()==Tab.charType)
				Code.put(0);
			else
				Code.put(1);
			Code.store(niz2);
			Code.loadConst(0);
			ispravna=Code.pc;
			Code.put(Code.dup2);
			Code.putFalseJump(Code.gt, 0);
			prepravi=Code.pc-2;
			Code.put(Code.dup);
			Code.load(niz);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			if(niz.getType().getElemType()==Tab.charType)
				Code.put(Code.baload);
			else
				Code.put(Code.aload);
			Code.store(((StatementMap)EQGREStart.getParent()).obj);
			Code.put(Code.dup);
			Code.load(niz2);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		
	}
	
	@Override
	public void visit(RPARENStart RPARENStart) {
		if(((StatementMap)RPARENStart.getParent()).obj.getType()==Tab.charType)
			Code.put(Code.bastore);
		else
			Code.put(Code.astore);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(ispravna);
		Code.fixup(prepravi);
		Code.put(Code.pop);
		Code.put(Code.pop);
	}
	
	
}		
