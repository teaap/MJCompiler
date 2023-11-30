// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class StatementIfElse extends Statement {

    private IFStart IFStart;
    private IfCondition IfCondition;
    private Statement Statement;
    private ELSEStart ELSEStart;
    private Statement Statement1;

    public StatementIfElse (IFStart IFStart, IfCondition IfCondition, Statement Statement, ELSEStart ELSEStart, Statement Statement1) {
        this.IFStart=IFStart;
        if(IFStart!=null) IFStart.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ELSEStart=ELSEStart;
        if(ELSEStart!=null) ELSEStart.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
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

    public ELSEStart getELSEStart() {
        return ELSEStart;
    }

    public void setELSEStart(ELSEStart ELSEStart) {
        this.ELSEStart=ELSEStart;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IFStart!=null) IFStart.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ELSEStart!=null) ELSEStart.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IFStart!=null) IFStart.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ELSEStart!=null) ELSEStart.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IFStart!=null) IFStart.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ELSEStart!=null) ELSEStart.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIfElse(\n");

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

        if(ELSEStart!=null)
            buffer.append(ELSEStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIfElse]");
        return buffer.toString();
    }
}
