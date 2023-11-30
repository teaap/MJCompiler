// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class StatementIf extends Statement {

    private IFStart IFStart;
    private IfCondition IfCondition;
    private Statement Statement;
    private IfNoElse IfNoElse;

    public StatementIf (IFStart IFStart, IfCondition IfCondition, Statement Statement, IfNoElse IfNoElse) {
        this.IFStart=IFStart;
        if(IFStart!=null) IFStart.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.IfNoElse=IfNoElse;
        if(IfNoElse!=null) IfNoElse.setParent(this);
    }

    public IFStart getIFStart() {
        return IFStart;
    }

    public void setIFStart(IFStart IFStart) {
        this.IFStart=IFStart;
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public IfNoElse getIfNoElse() {
        return IfNoElse;
    }

    public void setIfNoElse(IfNoElse IfNoElse) {
        this.IfNoElse=IfNoElse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IFStart!=null) IFStart.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(IfNoElse!=null) IfNoElse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IFStart!=null) IFStart.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(IfNoElse!=null) IfNoElse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IFStart!=null) IFStart.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(IfNoElse!=null) IfNoElse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIf(\n");

        if(IFStart!=null)
            buffer.append(IFStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfNoElse!=null)
            buffer.append(IfNoElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIf]");
        return buffer.toString();
    }
}
