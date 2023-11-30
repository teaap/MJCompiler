// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class VarDeclGOne extends VarDeclL {

    private String varOneBracket;

    public VarDeclGOne (String varOneBracket) {
        this.varOneBracket=varOneBracket;
    }

    public String getVarOneBracket() {
        return varOneBracket;
    }

    public void setVarOneBracket(String varOneBracket) {
        this.varOneBracket=varOneBracket;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclGOne(\n");

        buffer.append(" "+tab+varOneBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclGOne]");
        return buffer.toString();
    }
}
