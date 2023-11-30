// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAsExpr extends DesignatorStatement {

    private DsignatorAssign DsignatorAssign;

    public DesignatorAsExpr (DsignatorAssign DsignatorAssign) {
        this.DsignatorAssign=DsignatorAssign;
        if(DsignatorAssign!=null) DsignatorAssign.setParent(this);
    }

    public DsignatorAssign getDsignatorAssign() {
        return DsignatorAssign;
    }

    public void setDsignatorAssign(DsignatorAssign DsignatorAssign) {
        this.DsignatorAssign=DsignatorAssign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DsignatorAssign!=null) DsignatorAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DsignatorAssign!=null) DsignatorAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DsignatorAssign!=null) DsignatorAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAsExpr(\n");

        if(DsignatorAssign!=null)
            buffer.append(DsignatorAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAsExpr]");
        return buffer.toString();
    }
}
