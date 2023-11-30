// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFuncCall extends DesignatorStatement {

    private FactorFunc FactorFunc;
    private ActParsL ActParsL;

    public DesignatorFuncCall (FactorFunc FactorFunc, ActParsL ActParsL) {
        this.FactorFunc=FactorFunc;
        if(FactorFunc!=null) FactorFunc.setParent(this);
        this.ActParsL=ActParsL;
        if(ActParsL!=null) ActParsL.setParent(this);
    }

    public FactorFunc getFactorFunc() {
        return FactorFunc;
    }

    public void setFactorFunc(FactorFunc FactorFunc) {
        this.FactorFunc=FactorFunc;
    }

    public ActParsL getActParsL() {
        return ActParsL;
    }

    public void setActParsL(ActParsL ActParsL) {
        this.ActParsL=ActParsL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorFunc!=null) FactorFunc.accept(visitor);
        if(ActParsL!=null) ActParsL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorFunc!=null) FactorFunc.traverseTopDown(visitor);
        if(ActParsL!=null) ActParsL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorFunc!=null) FactorFunc.traverseBottomUp(visitor);
        if(ActParsL!=null) ActParsL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFuncCall(\n");

        if(FactorFunc!=null)
            buffer.append(FactorFunc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsL!=null)
            buffer.append(ActParsL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFuncCall]");
        return buffer.toString();
    }
}
