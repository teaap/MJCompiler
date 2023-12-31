package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.ActParsOne;
import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.CondFactExpr;
import rs.ac.bg.etf.pp1.ast.CondFactList;
import rs.ac.bg.etf.pp1.ast.CondFactOne;
import rs.ac.bg.etf.pp1.ast.CondFactRelop;
import rs.ac.bg.etf.pp1.ast.CondTermOne;
import rs.ac.bg.etf.pp1.ast.DesigError;
import rs.ac.bg.etf.pp1.ast.DesigExprDouble;
import rs.ac.bg.etf.pp1.ast.DesigExprOne;
import rs.ac.bg.etf.pp1.ast.DesigIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorAsExpr;
import rs.ac.bg.etf.pp1.ast.DesignatorFuncCall;
import rs.ac.bg.etf.pp1.ast.DesignatorMinusMinus;
import rs.ac.bg.etf.pp1.ast.DesignatorPlusPlus;
import rs.ac.bg.etf.pp1.ast.DsignatorAss;
import rs.ac.bg.etf.pp1.ast.ExprAddop;
import rs.ac.bg.etf.pp1.ast.ExprMinus;
import rs.ac.bg.etf.pp1.ast.ExprNoMinus;
import rs.ac.bg.etf.pp1.ast.FactorBoolConst;
import rs.ac.bg.etf.pp1.ast.FactorCharConst;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.FactorExprExpr;
import rs.ac.bg.etf.pp1.ast.FactorFunc;
import rs.ac.bg.etf.pp1.ast.FactorFuncCall;
import rs.ac.bg.etf.pp1.ast.FactorNewBracket;
import rs.ac.bg.etf.pp1.ast.FactorNumConst;
import rs.ac.bg.etf.pp1.ast.FormParamDeclVarNo;
import rs.ac.bg.etf.pp1.ast.FormParamDeclVarOne;
import rs.ac.bg.etf.pp1.ast.FormParamDeclVarTwo;
import rs.ac.bg.etf.pp1.ast.ListCondTerm;
import rs.ac.bg.etf.pp1.ast.ListExpr;
import rs.ac.bg.etf.pp1.ast.MethodDeclReturn;
import rs.ac.bg.etf.pp1.ast.MethodDeclType;
import rs.ac.bg.etf.pp1.ast.MethodReturnTypeType;
import rs.ac.bg.etf.pp1.ast.MethodReturnTypeVoid;
import rs.ac.bg.etf.pp1.ast.NoDesig;
import rs.ac.bg.etf.pp1.ast.NumConst;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.RelopEqual;
import rs.ac.bg.etf.pp1.ast.RelopNoEqual;
import rs.ac.bg.etf.pp1.ast.StatementBreak;
import rs.ac.bg.etf.pp1.ast.StatementContinue;
import rs.ac.bg.etf.pp1.ast.StatementExpr;
import rs.ac.bg.etf.pp1.ast.StatementMap;
import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.StatementRead;
import rs.ac.bg.etf.pp1.ast.StatementRetExp;
import rs.ac.bg.etf.pp1.ast.StatementWhile;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermFactor;
import rs.ac.bg.etf.pp1.ast.TermFactorMulop;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VarDeclGOne;
import rs.ac.bg.etf.pp1.ast.VarDeclGTwo;
import rs.ac.bg.etf.pp1.ast.VarDeclNo;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.WHILEStart;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	boolean mainFound=false;
	int brParam=0;
	Struct currentType = null;
	int nVars=0;
	int whileCnt=0;
	
	public int getnVars() {
		return nVars;
	}
	
	private  Stack<ArrayList<Struct>> paramFunc = new Stack<>();
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	@Override
	public void visit(ProgName ProgName) {
		ProgName.obj = Tab.insert(Obj.Prog, ProgName.getProgName(), Tab.noType);
		report_info("Ime programa: "+ProgName.getProgName(), ProgName);
		Tab.openScope();
	}
	
	@Override
	public void visit(Program Program) {
		nVars=Tab.currentScope.getnVars();
		if(!mainFound ){
			report_error("Greska! main() nije pronadjen!", null);
		}
		Tab.chainLocalSymbols(Program.getProgName().obj);
		Tab.closeScope();
	}
	
	@Override
	public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Grska! Nije pronadjen " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska! Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    	report_info("Pronadjen tip "+type.getTypeName(), type);
    	currentType=type.struct;
    }
	
	@Override
	public void visit(NumConst numconst) {
		if(!this.currentType.equals(Tab.intType)){
			report_error("Greska! Tip nije odgovarajuci!", numconst);
		}
		else if(Tab.find(numconst.getNumName())!=Tab.noObj) {
			report_error("Greska! Konstanta " + numconst.getNumName() + " je vec definisana!", numconst);
		}
		else {
			numconst.obj = Tab.insert(Obj.Con, numconst.getNumName(), Tab.intType);
			numconst.obj.setAdr(numconst.getNumValue());
			report_info("Deklarisana konstanta: "+numconst.getNumName()+" = "+(numconst.getNumValue())+".", numconst);
		}		
	}
	
	@Override
	public void visit(CharConst charconst) {
		if(!this.currentType.equals(Tab.charType)){
			report_error("Greska: Tip nije odgovarajuci!", charconst);
		}
		else if(Tab.find(charconst.getCharName())!=Tab.noObj) {
			report_error("Greska: Konstanta " + charconst.getCharName() + " je vec definisana!", charconst);
		}
		else {
			charconst.obj = Tab.insert(Obj.Con, charconst.getCharName(), Tab.charType);
			charconst.obj.setAdr(charconst.getCharValue());
			report_info("Deklarisana konstanta: "+charconst.getCharName()+" = "+(charconst.getCharValue())+".", charconst);
		}	
	}
	
	@Override
	public void visit(BoolConst boolconst) {
		if(!this.currentType.equals(Tip.boolType())){
			report_error("Greska: Tip nije odgovarajuci!", boolconst);
		}
		else if(Tab.find(boolconst.getBoolName())!=Tab.noObj) {
			report_error("Greska: Konstanta " + boolconst.getBoolName() + " je vec definisana!", boolconst);
		}
		else {
			boolconst.obj = Tab.insert(Obj.Con, boolconst.getBoolName(), Tip.boolType());
			if(boolconst.getBoolValue())
				boolconst.obj.setAdr(1);
			else
				boolconst.obj.setAdr(0);
			report_info("Deklarisana konstanta: "+boolconst.getBoolName()+" = "+(boolconst.getBoolValue()?"true":"false")+".", boolconst);
		}	
	}
	
	@Override
	public void visit(VarDeclNo VarDeclNo) {
		if(currentType.equals(Tab.noType)) {
			report_error("Greska! Tip je nepostojeci",VarDeclNo);
			return;
		}
		if(Tab.currentScope.findSymbol(VarDeclNo.getVarNoBracket()) != null) {
			report_error("Greska! Promenljiva je vec deklarisana",VarDeclNo);
			return;
		}
		report_info("Deklarisana promenljiva "+VarDeclNo.getVarNoBracket(), VarDeclNo);
		Tab.insert(Obj.Var, VarDeclNo.getVarNoBracket(), currentType);
	}
	
	@Override
	public void visit(VarDeclGOne VarDeclGOne) {
		if(currentType.equals(Tab.noType)) {
			report_error("Tip je nepostojeci",VarDeclGOne);
			return;
		}
		if(Tab.currentScope.findSymbol(VarDeclGOne.getVarOneBracket()) != null) {
			report_error("Promenljiva je vec deklarisana",VarDeclGOne);
			return;
		}
		Struct novi=new Struct(Struct.Array,currentType);
		Tab.insert(Obj.Var, VarDeclGOne.getVarOneBracket(), novi);
		report_info("Deklarisana promenljiva " + VarDeclGOne.getVarOneBracket(), VarDeclGOne);
	}
	
	@Override
	public void visit(VarDeclGTwo VarDeclGTwo) {
		if(currentType.equals(Tab.noType)) {
			report_error("Tip je nepostojeci",VarDeclGTwo);
			return;
		}
		if(Tab.currentScope.findSymbol(VarDeclGTwo.getVarTwoBracket()) != null) {
			report_error("Promenljiva je vec deklarisana",VarDeclGTwo);
			return;
		}
		Struct priv=new Struct(Struct.Array, currentType);
		Struct novi=new Struct(Struct.Array, priv);
		report_info("Deklarisana promenljiva "+VarDeclGTwo.getVarTwoBracket(), VarDeclGTwo);
		Tab.insert(Obj.Var, VarDeclGTwo.getVarTwoBracket(), novi);
	}
	
	@Override
	public void visit(FormParamDeclVarTwo FormParamDeclVarTwo) {
		brParam++;
		if(currentType.equals(Tab.noType)) {
			report_error("Tip je nepostojeci",FormParamDeclVarTwo);
			return;
		}
		if(Tab.find(FormParamDeclVarTwo.getNameTwoBracket())!=Tab.noObj){
			if(Tab.currentScope.findSymbol(FormParamDeclVarTwo.getNameTwoBracket()) != null) {
				report_error("Promenljiva je vec parametar",FormParamDeclVarTwo);
				return;
			}
		}
		Struct priv=new Struct(Struct.Array, currentType);
		Struct novi=new Struct(Struct.Array, priv);
		report_info("Parametar "+FormParamDeclVarTwo.getNameTwoBracket(), FormParamDeclVarTwo);
		Tab.insert(Obj.Var, FormParamDeclVarTwo.getNameTwoBracket(), novi);
	}
	
	@Override
	public void visit(FormParamDeclVarOne FormParamDeclVarOne) {
		brParam++;
		if(currentType.equals(Tab.noType)) {
			report_error("Tip je nepostojeci",FormParamDeclVarOne);
			return;
		}
		if(Tab.find(FormParamDeclVarOne.getNameOneBracket())!=Tab.noObj){
			if(Tab.currentScope.findSymbol(FormParamDeclVarOne.getNameOneBracket()) != null) {
				report_error("Promenljiva je vec parametar",FormParamDeclVarOne);
				return;
			}
		}
		report_info("Parametar "+FormParamDeclVarOne.getNameOneBracket(), FormParamDeclVarOne);
		Struct priv=new Struct(Struct.Array, currentType);
		Tab.insert(Obj.Var, FormParamDeclVarOne.getNameOneBracket(), priv);
	}
	
	@Override
	public void visit(FormParamDeclVarNo FormParamDeclVarNo) {
		brParam++;
		if(currentType.equals(Tab.noType)) {
			report_error("Tip je nepostojeci",FormParamDeclVarNo);
			return;
		}
		if(Tab.find(FormParamDeclVarNo.getNameNoBracket())!=Tab.noObj){
			if(Tab.currentScope.findSymbol(FormParamDeclVarNo.getNameNoBracket()) != null) {
				report_error("Promenljiva je vec parametar",FormParamDeclVarNo);
				return;
			}
		}
		report_info("Parametar "+FormParamDeclVarNo.getNameNoBracket(), FormParamDeclVarNo);
		Tab.insert(Obj.Var, FormParamDeclVarNo.getNameNoBracket(), currentType);
	}
	
	@Override
	public void visit(MethodDeclReturn MethodDeclReturn) {
		returnFound=false;
		if(Tab.noType==currentType) {
			report_error("Povratna vrednost nije validna",MethodDeclReturn);
			currentMethod=Tab.noObj;
			MethodDeclReturn.obj=Tab.noObj;
			Tab.openScope();
			return;
		}
		
		if(Tab.currentScope.findSymbol(MethodDeclReturn.getNameFuncT()) != null){
			report_error("Funkcija je vec deklarisana",MethodDeclReturn);
			return;
		}
		currentMethod = Tab.insert(Obj.Meth, MethodDeclReturn.getNameFuncT(), MethodDeclReturn.getMethodReturnType().struct);
		MethodDeclReturn.obj = currentMethod;
		Tab.openScope();
		report_info("Deklarisana funkcija " + MethodDeclReturn.getNameFuncT(), MethodDeclReturn);
	}
	
    public boolean passed(){
    	return !errorDetected;
    }
    
    @Override
    public void visit(MethodReturnTypeType MethodReturnTypeType) {
    	MethodReturnTypeType.struct = currentType;
    }
    
    @Override
    public void visit(CondFactOne CondFactOne) {
    	CondFactOne.struct = CondFactOne.getCondFact().struct;
    }
    
    @Override
    public void visit(MethodReturnTypeVoid MethodReturnTypeVoid) {
    	currentType=Tip.voidType();
    	MethodReturnTypeVoid.struct = Tip.voidType();
    }
    
    @Override
    public void visit(ListCondTerm ListCondTerm) {
    	if(ListCondTerm.getCondTerm().struct!=Tip.boolType())
    		report_error("Greska! Tip izraza mora biti bool", ListCondTerm);
    	ListCondTerm.struct = ListCondTerm.getCondTerm().struct;
    }
    
    @Override
    public void visit(CondFactRelop CondFactRelop) {
    	Struct prvi = CondFactRelop.getExpr().struct;
    	Struct drugi = CondFactRelop.getExpr1().struct;
    	
		if(prvi.getKind()==Struct.Array) {
			if(!((CondFactRelop.getRelop() instanceof RelopEqual) || (CondFactRelop.getRelop() instanceof RelopNoEqual))) {
				report_error("Greska! Operacije moraju biti == ili !=", CondFactRelop);
			}
			CondFactRelop.struct = Tip.boolType();
		}
	    if(!prvi.compatibleWith(drugi)) { 
	    		report_error("Tipovi nisu kompatibilni", CondFactRelop);
	    		CondFactRelop.struct = Tip.boolType();
	    }
	    else CondFactRelop.struct = Tip.boolType();
    }
    
   
    @Override
    public void visit(MethodDeclType MethodDeclType) {
    	currentMethod.setLevel(brParam);
    	
    	if("main".equals(currentMethod.getName()) && brParam==0) {
    			mainFound=true;
    	}
    	
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	returnFound=false;
    	brParam=0;
    	currentMethod=null;
    }
    
    @Override
    public void visit(CondFactExpr CondFactExpr) {
    	CondFactExpr.struct = CondFactExpr.getExpr().struct;
    }
    
    @Override
    public void visit(CondTermOne CondTermOne) {
    	if(CondTermOne.getCondTerm().struct!=Tip.boolType())
    		report_error("Greska! Izraz mora biti bool", CondTermOne);
    	CondTermOne.struct = CondTermOne.getCondTerm().struct;
    }
    
    @Override
    public void visit(ExprNoMinus ExprNoMinus) {
    	ExprNoMinus.struct = ExprNoMinus.getTerm().struct;
    }
    
    @Override
    public void visit(CondFactList CondFactList) {
    	CondFactList.struct = CondFactList.getCondFact().struct;
    }
    
    @Override
    public void visit(ExprMinus ExprMinus) {
    	ExprMinus.struct = ExprMinus.getTerm().struct;
    	if(ExprMinus.struct!=Tab.intType) {
    		ExprMinus.struct=Tab.noType;
    		report_error("Greska! Tip mora biti int!", ExprMinus);
    	}
    }
    
    @Override
    public void visit(ExprAddop ExprAddop) {
    	Struct prvi=ExprAddop.getExpr().struct;
    	Struct drugi=ExprAddop.getTerm().struct;
    	if(prvi.equals(drugi) && drugi==Tab.intType) {
    		ExprAddop.struct = Tab.intType; 
    		return;
    	}
    	ExprAddop.struct = Tab.noType;
    	report_error("Greska! Neodgovarajuci tipovi pri sabiranju!", ExprAddop);
    }
    
    @Override
    public void visit(TermFactor TermFactor) {
    	TermFactor.struct = TermFactor.getFactor().struct;
    }
    
    Struct returnType(Struct src) {
    	Struct d;
    	if(src.getKind()==Struct.Array 
    			&& src.getElemType().getKind()==Struct.Array) 
    		d=src.getElemType().getElemType();
    	else if(src.getKind()==Struct.Array)
    		d=src.getElemType();
    	else
    		d=src;
    	return d;
    }
    
    @Override
    public void visit(TermFactorMulop TermFactorMulop) {
    	Struct prvi=TermFactorMulop.getTerm().struct;
    	Struct drugi=TermFactorMulop.getFactor().struct;
    	if(prvi.equals(drugi) && drugi==Tab.intType) {
    		TermFactorMulop.struct = Tab.intType; 
    		return;
    	}
    	TermFactorMulop.struct = Tab.noType;
    	report_error("Greska! Neodgovarajuci tipovi pri mnozenju!", TermFactorMulop);
    }
    
    @Override
    public void visit(WHILEStart WHILEStart) {
    	whileCnt++;
    	return;
    }
    
    @Override
    public void visit(StatementWhile StatementWhile) {
    	whileCnt--;
    	return;
    }
    
    @Override
    public void visit(StatementBreak StatementBreak) {
    	if(whileCnt==0)
    		report_error("Greska! Break se ne sme koristiti van petlje", StatementBreak);
    }
    
    @Override
    public void visit(StatementContinue StatementContinue) {
    	if(whileCnt==0)
    		report_error("Greska! Continue se ne sme koristiti van petlje", StatementContinue);
    }
    
    @Override
    public void visit(StatementRetExp StatementRetExp) {
    	returnFound=true;
    	if(!currentMethod.getType().equals(StatementRetExp.getExpr().struct)){
    		report_error("Greska! Tip povratne vrednosti funkcije se ne poklapa sa tipom izraza u return naredbi", StatementRetExp);
    		
    		return;
    	}
    }
    
    @Override
    public void visit(StatementExpr StatementExpr) {
    	if(StatementExpr.getExpr().struct!=Tab.intType) {
    		if(StatementExpr.getExpr().struct!=Tab.charType) {
    			if(StatementExpr.getExpr().struct!=Tip.boolType()) {
    				report_error("Greska! Parametar mora biti tipa ili int ili bool ili char", StatementExpr);
    				return ;
    			}
    		}
    	}
    }
    
    @Override
    public void visit(StatementRead StatementRead) {
    	if(StatementRead.getDesignator().obj.getKind()!=Obj.Var) {
    		if(StatementRead.getDesignator().obj.getKind()!=Obj.Elem) {
    			if(StatementRead.getDesignator().obj.getKind()!=Obj.Fld) {
    				report_error("Greska! Parametar mora biti ili promenljiva ili element niza ili polje unutar objekta", StatementRead);
    				return ;
    			}
    		}
    	}
    	if(StatementRead.getDesignator().obj.getType()!=Tab.intType) {
    		if(StatementRead.getDesignator().obj.getType()!=Tab.charType) {
    			if(StatementRead.getDesignator().obj.getType()!=Tip.boolType()) {
    				report_error("Greska! Parametar mora biti tipa ili int ili bool ili char", StatementRead);
    				return ;
    			}
    		}
    	}
    }
    
    @Override
    public void visit(StatementPrint StatementPrint) {
    	if(StatementPrint.getExpr().struct!=Tab.intType) {
    		if(StatementPrint.getExpr().struct!=Tab.charType) {
    			if(StatementPrint.getExpr().struct!=Tip.boolType()) {
    				report_error("Greska! Parametar mora biti tipa ili int ili bool ili char", StatementPrint);
    				return ;
    			}
    		}
    	}
    }
    
    boolean checkassign(Struct src, Struct dst)
    {
    	Struct d,l;
    	if(src.getKind()==Struct.Array 
    			&& src.getElemType().getKind()==Struct.Array) 
    		d=src.getElemType().getElemType();
    	else if(src.getKind()==Struct.Array)
    		d=src.getElemType();
    	else
    		d=src;
    	
    	if(dst.getKind()==Struct.Array 
    			&& dst.getElemType().getKind()==Struct.Array) 
    		l=dst.getElemType().getElemType();
    	else if(dst.getKind()==Struct.Array)
    		l=dst.getElemType();
    	else
    		l=dst;
    	
    	if(!d.assignableTo(l))
    	{
    		return false;
    	}
    	return true;
    }
    
    @Override
    public void visit(DsignatorAss DsignatorAss) {
    	Obj leva=DsignatorAss.getDesignator().obj;    
    	
    	if(leva.getKind()!=Obj.Var) {
    		if(leva.getKind()!=Obj.Elem) {
    			if(leva.getKind()!=Obj.Fld) {
    				report_error("Greska! Leva strana mora biti ili promenljiva ili element niza ili polje unutar objekta", DsignatorAss);
    			}
    		}
    	}
    	
    	if(!leva.getType().assignableTo(DsignatorAss.getExpr().struct))
    	{
    		report_error("Greska! Leva i desna strana nisu kompatibilne!", DsignatorAss);
    	}
    	else
    	{
    		report_info("Dodela vrednosti", DsignatorAss);
    	}
    }
    
    @Override
    public void visit(DesignatorPlusPlus DesignatorPlusPlus) {
    	Obj leva=DesignatorPlusPlus.getDesignator().obj;
    	if(DesignatorPlusPlus.getDesignator().obj.getKind()!=Obj.Var) {
    		if(DesignatorPlusPlus.getDesignator().obj.getKind()!=Obj.Elem) {
    			if(DesignatorPlusPlus.getDesignator().obj.getKind()!=Obj.Fld) {
    				report_error("Greska! Leva strana mora biti ili promenljiva ili element niza ili polje unutar objekta", DesignatorPlusPlus);
    			}
    		}
    	}
    	if(DesignatorPlusPlus.getDesignator().obj.getType()!=Tab.intType) {
    		report_error("Greska! Vrednost nije tipa int", DesignatorPlusPlus);
    	}
    }
    
    @Override
    public void visit(DesignatorMinusMinus DesignatorMinusMinus) {
    	Obj leva=DesignatorMinusMinus.getDesignator().obj;
    	if(DesignatorMinusMinus.getDesignator().obj.getKind()!=Obj.Var) {
    		if(DesignatorMinusMinus.getDesignator().obj.getKind()!=Obj.Elem) {
    			if(DesignatorMinusMinus.getDesignator().obj.getKind()!=Obj.Fld) {
    				report_error("Greska! Leva strana mora biti ili promenljiva ili element niza ili polje unutar objekta", DesignatorMinusMinus);
    			}
    		}
    	}
    	if(DesignatorMinusMinus.getDesignator().obj.getType()!=Tab.intType) {
    		report_error("Greska! Vrednost nije tipa int", DesignatorMinusMinus);
    	}
    }
    
    @Override
    public void visit(DesigError DesigError) {
    	report_error("Greska! U zagradama", DesigError);
    }
    
    @Override
    public void visit(NoDesig NoDesig) {
    	
    	if(Tab.find(NoDesig.getDesigIdent().getNameDesig())==Tab.noObj) {
    		report_error("Greska! Simbol nije pronadjen", NoDesig);
    		NoDesig.obj=Tab.noObj;
    		return;
    	}
    	NoDesig.obj=Tab.find(NoDesig.getDesigIdent().getNameDesig());
    	
    }
    
    @Override
    public void visit(DesigIdent DesigIdent) {
    	DesigIdent.obj=Tab.find(DesigIdent.getNameDesig());
    }
    
    @Override
    public void visit(DesigExprOne DesigExprOne) {
    	if(DesigExprOne.getDesigIdent().obj==Tab.noObj) {
    		DesigExprOne.obj=Tab.noObj;
    		report_error("Greska! Simbol nije pronadjen", DesigExprOne);
    		return;
    	}
    	if(DesigExprOne.getExpr().struct!=Tab.intType) {
    		DesigExprOne.obj=Tab.noObj;
    		report_error("Greska! Tip mora biti int", DesigExprOne);
    		return;
    	}
    	if(DesigExprOne.getDesigIdent().obj.getType().getKind()!=Struct.Array ) {
    		report_error("Greska! Tip mora biti niz", DesigExprOne);
    		DesigExprOne.obj=Tab.noObj;
    		return;
    	}
    	DesigExprOne.obj=new Obj(Obj.Elem, DesigExprOne.getDesigIdent().obj.getName(), DesigExprOne.getDesigIdent().obj.getType().getElemType());
    }
    
    @Override
    public void visit(DesigExprDouble DesigExprDouble) {
    	if(DesigExprDouble.getDesigIdent().obj==Tab.noObj) {
    		DesigExprDouble.obj=Tab.noObj;
    		report_error("Greska! Simbol nije pronadjen", DesigExprDouble);
    		return;
    	}
    	if(DesigExprDouble.getExpr().struct!=Tab.intType || DesigExprDouble.getExpr1().struct!=Tab.intType) {
    		report_error("Greska! Tip indeksa mora biti int", DesigExprDouble);
    		DesigExprDouble.obj=Tab.noObj;
    		return;
    	}
    	if(DesigExprDouble.getDesigIdent().obj.getType().getKind()!=Struct.Array || DesigExprDouble.getDesigIdent().obj.getType().getElemType().getKind()!=Struct.Array) {
    		report_error("Greska! Tip mora biti matrica", DesigExprDouble);
    		DesigExprDouble.obj=Tab.noObj;
    		return;
    	}
    	DesigExprDouble.obj=new Obj(Obj.Fld, DesigExprDouble.getDesigIdent().obj.getName(), DesigExprDouble.getDesigIdent().obj.getType().getElemType().getElemType());
    	
    }
    
    @Override
    public void visit(FactorDesignator FactorDesignator) {
    		FactorDesignator.struct = FactorDesignator.getDesignator().obj.getType();
    }
    
    @Override
    public void visit(FactorNewBracket FactorNewBracket) {
    	if(FactorNewBracket.getExpr().struct!=Tab.intType) {
    		report_error("Greska! Broj mora biti int", FactorNewBracket);
    		FactorNewBracket.struct=Tab.noType;
    	}
    	else
    		FactorNewBracket.struct=new Struct(Struct.Array, currentType);
    }
    
    @Override
    public void visit(FactorExpr FactorExpr) {  	
    	FactorExpr.struct=FactorExpr.getExpr().struct;
    }
    
    @Override
    public void visit(FactorNumConst FactorNumConst) {  	
    	FactorNumConst.struct=Tab.intType;
    }
    
    @Override
    public void visit(FactorCharConst FactorCharConst) {  	
    	FactorCharConst.struct=Tab.charType;
    }
    
    @Override
    public void visit(FactorBoolConst FactorBoolConst) {  	
    	FactorBoolConst.struct=Tip.boolType();
    }
    
    @Override
    public void visit(FactorExprExpr FactorExprExpr) {
    	if(FactorExprExpr.getExpr().struct!=Tab.intType || FactorExprExpr.getExpr1().struct!=Tab.intType) {
    		report_error("Greska! Broj mora biti int", FactorExprExpr);
    		FactorExprExpr.struct=Tab.noType;
    	}
    	else {
    		Struct priv=new Struct(Struct.Array, currentType);
    		Struct novi=new Struct(Struct.Array, priv);
    		FactorExprExpr.struct=novi;
    	}
    }
    
    @Override
    public void visit(FactorFunc FactorFunc) {
    	paramFunc.add(new ArrayList<Struct>());
    	FactorFunc.obj=FactorFunc.getDesignator().obj;
    }
    
    @Override
    public void visit(ActParsOne ActParsOne) {
    	paramFunc.peek().add(ActParsOne.getExpr().struct);
    }
    
    @Override
    public void visit(ListExpr ListExpr) {
    	paramFunc.peek().add(ListExpr.getExpr().struct);
    }
    
    @Override
    public void visit(FactorFuncCall FactorFuncCall) {
    	
    	if(FactorFuncCall.getFactorFunc().getDesignator().obj.getKind()!=Obj.Meth) {
    		report_error("Greska! Treba da bude metoda", FactorFuncCall);
    		paramFunc.pop();
    		FactorFuncCall.struct=Tab.noType;
    	}
    	else {
    		FactorFuncCall.struct=FactorFuncCall.getFactorFunc().getDesignator().obj.getType();
    		ArrayList<Struct> parametri=paramFunc.pop();
    		//form
    		Iterator<Obj> ilokalniparam = FactorFuncCall.getFactorFunc().getDesignator().obj.getLocalSymbols().iterator();
    		Iterator<Struct> iparametri = parametri.iterator();
    		int cnt;
    		
    		for(cnt=FactorFuncCall.getFactorFunc().getDesignator().obj.getLevel(); iparametri.hasNext() && cnt>0;cnt--) {
    			Struct prvi=ilokalniparam.next().getType();
    			Struct drugi=iparametri.next();
    			
    			if(!drugi.assignableTo(prvi)) {
    				report_error("Greska! Nisu kompatibilni tipovi", FactorFuncCall);
    				return;
    			}	
    		}
    		if(cnt!=0 || iparametri.hasNext()) {
    			report_error("Greska! Greska u broju parametara", FactorFuncCall);
    			return;
    		}
    	}
    }
    
    @Override
    public void visit(DesignatorFuncCall DesignatorFuncCall) {
    	
    	if(DesignatorFuncCall.getFactorFunc().getDesignator().obj.getKind()!=Obj.Meth) {
    		report_error("Greska! Treba da bude metoda", DesignatorFuncCall);
    		paramFunc.pop();
    	}
    	else {
    		
    		ArrayList<Struct> parametri=paramFunc.pop();
    		//form
    		Iterator<Obj> ilokalniparam = DesignatorFuncCall.getFactorFunc().getDesignator().obj.getLocalSymbols().iterator();
    		Iterator<Struct> iparametri = parametri.iterator();
    		int cnt;
    		
    		for(cnt=DesignatorFuncCall.getFactorFunc().getDesignator().obj.getLevel(); iparametri.hasNext() && cnt>0;cnt--) {
    			Struct prvi=ilokalniparam.next().getType();
    			Struct drugi=iparametri.next();
    			
    			if(!drugi.assignableTo(prvi)) {
    				report_error("Greska! Nisu kompatibilni tipovi", DesignatorFuncCall);
    				return;
    			}	
    		}
    		if(cnt!=0 || iparametri.hasNext()) {
    			report_error("Greska! Greska u broju parametara", DesignatorFuncCall);
    			return;
    		}
    	}
    }
    
    @Override
    public void visit(StatementMap StatementMap) {
    	StatementMap.obj=Tab.find(StatementMap.getNameIdent());
    	Struct tipD2=StatementMap.getDesignator1().obj.getType();
    	if(StatementMap.getDesignator1().obj.getType().getElemType().getKind()==Struct.Array 
    			&& StatementMap.getDesignator1().obj.getType().getKind()==Struct.Array) {
    		report_error("Greska! Ne sme dvodimenzionalni niz", StatementMap);
    	}
    	if(Tab.find(StatementMap.getNameIdent())==Tab.noObj) {
    		report_error("Greska! Nije definisana promenljiva", StatementMap);
    	}
    	if(!tipD2.getElemType().equals(Tab.find(StatementMap.getNameIdent()).getType())) {
    		report_info("prvi: "+tipD2.getKind()+" drugi: "+Tab.find(StatementMap.getNameIdent()).getType().getKind(), StatementMap);
    		report_error("Greska! Nisu kompatibilni tipovi", StatementMap);
    	}
    	if(StatementMap.getDesignator1().obj.getType().getElemType().getKind()!=Struct.Bool) {
    		if(StatementMap.getDesignator1().obj.getType().getElemType().getKind()!=Struct.Char) {
    			if(StatementMap.getDesignator1().obj.getType().getElemType().getKind()!=Struct.Int) {
    				report_error("Greska! Nisu elementi moraju biti ili num ili bool ili char", StatementMap);
    			}
    		}
    	}
    	if(!(StatementMap.getExpr().struct).equals(Tab.find(StatementMap.getNameIdent()).getType())) {
    		report_error("Greska! Izraz nije odgovarajuceg tipa", StatementMap);
    		
    	}
    }
    
    
    
}	
