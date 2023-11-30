// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class VarDeclGTwo extends VarDeclL {

    private String varTwoBracket;

    public VarDeclGTwo (String varTwoBracket) {
        this.varTwoBracket=varTwoBracket;
    }

    public String getVarTwoBracket() {
        return varTwoBracket;
    }

    public void setVarTwoBracket(String varTwoBracket) {
        this.varTwoBracket=varTwoBracket;
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
        buffer.append("VarDeclGTwo(\n");

        buffer.append(" "+tab+varTwoBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclGTwo]");
        return buffer.toString();
    }
}
