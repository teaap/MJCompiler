// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:12


package rs.ac.bg.etf.pp1.ast;

public class BrackDesigList extends BrackDesig {

    private BrackDesig BrackDesig;
    private Expr Expr;

    public BrackDesigList (BrackDesig BrackDesig, Expr Expr) {
        this.BrackDesig=BrackDesig;
        if(BrackDesig!=null) BrackDesig.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public BrackDesig getBrackDesig() {
        return BrackDesig;
    }

    public void setBrackDesig(BrackDesig BrackDesig) {
        this.BrackDesig=BrackDesig;
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
        if(BrackDesig!=null) BrackDesig.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BrackDesig!=null) BrackDesig.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BrackDesig!=null) BrackDesig.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BrackDesigList(\n");

        if(BrackDesig!=null)
            buffer.append(BrackDesig.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BrackDesigList]");
        return buffer.toString();
    }
}
