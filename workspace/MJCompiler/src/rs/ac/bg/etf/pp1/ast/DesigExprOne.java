// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:12


package rs.ac.bg.etf.pp1.ast;

public class DesigExprOne extends Designator {

    private DesigIdent DesigIdent;
    private Expr Expr;

    public DesigExprOne (DesigIdent DesigIdent, Expr Expr) {
        this.DesigIdent=DesigIdent;
        if(DesigIdent!=null) DesigIdent.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesigIdent getDesigIdent() {
        return DesigIdent;
    }

    public void setDesigIdent(DesigIdent DesigIdent) {
        this.DesigIdent=DesigIdent;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigIdent!=null) DesigIdent.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigExprOne(\n");

        if(DesigIdent!=null)
            buffer.append(DesigIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigExprOne]");
        return buffer.toString();
    }
}
