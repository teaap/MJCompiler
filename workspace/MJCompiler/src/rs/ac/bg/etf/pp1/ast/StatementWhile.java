// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class StatementWhile extends Statement {

    private WHILEStart WHILEStart;
    private WhileCondition WhileCondition;
    private Statement Statement;

    public StatementWhile (WHILEStart WHILEStart, WhileCondition WhileCondition, Statement Statement) {
        this.WHILEStart=WHILEStart;
        if(WHILEStart!=null) WHILEStart.setParent(this);
        this.WhileCondition=WhileCondition;
        if(WhileCondition!=null) WhileCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public WHILEStart getWHILEStart() {
        return WHILEStart;
    }

    public void setWHILEStart(WHILEStart WHILEStart) {
        this.WHILEStart=WHILEStart;
    }

    public WhileCondition getWhileCondition() {
        return WhileCondition;
    }

    public void setWhileCondition(WhileCondition WhileCondition) {
        this.WhileCondition=WhileCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WHILEStart!=null) WHILEStart.accept(visitor);
        if(WhileCondition!=null) WhileCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WHILEStart!=null) WHILEStart.traverseTopDown(visitor);
        if(WhileCondition!=null) WhileCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WHILEStart!=null) WHILEStart.traverseBottomUp(visitor);
        if(WhileCondition!=null) WhileCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementWhile(\n");

        if(WHILEStart!=null)
            buffer.append(WHILEStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileCondition!=null)
            buffer.append(WhileCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementWhile]");
        return buffer.toString();
    }
}
