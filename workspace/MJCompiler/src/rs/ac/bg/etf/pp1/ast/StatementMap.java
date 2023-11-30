// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class StatementMap extends Statement {

    private Designator Designator;
    private Designator Designator1;
    private String nameIdent;
    private EQGREStart EQGREStart;
    private Expr Expr;
    private RPARENStart RPARENStart;

    public StatementMap (Designator Designator, Designator Designator1, String nameIdent, EQGREStart EQGREStart, Expr Expr, RPARENStart RPARENStart) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
        this.nameIdent=nameIdent;
        this.EQGREStart=EQGREStart;
        if(EQGREStart!=null) EQGREStart.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.RPARENStart=RPARENStart;
        if(RPARENStart!=null) RPARENStart.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public String getNameIdent() {
        return nameIdent;
    }

    public void setNameIdent(String nameIdent) {
        this.nameIdent=nameIdent;
    }

    public EQGREStart getEQGREStart() {
        return EQGREStart;
    }

    public void setEQGREStart(EQGREStart EQGREStart) {
        this.EQGREStart=EQGREStart;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public RPARENStart getRPARENStart() {
        return RPARENStart;
    }

    public void setRPARENStart(RPARENStart RPARENStart) {
        this.RPARENStart=RPARENStart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
        if(EQGREStart!=null) EQGREStart.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(RPARENStart!=null) RPARENStart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
        if(EQGREStart!=null) EQGREStart.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(RPARENStart!=null) RPARENStart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        if(EQGREStart!=null) EQGREStart.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(RPARENStart!=null) RPARENStart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementMap(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+nameIdent);
        buffer.append("\n");

        if(EQGREStart!=null)
            buffer.append(EQGREStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RPARENStart!=null)
            buffer.append(RPARENStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementMap]");
        return buffer.toString();
    }
}
