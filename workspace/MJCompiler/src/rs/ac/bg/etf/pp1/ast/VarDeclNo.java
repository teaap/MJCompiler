// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class VarDeclNo extends VarDeclL {

    private String varNoBracket;

    public VarDeclNo (String varNoBracket) {
        this.varNoBracket=varNoBracket;
    }

    public String getVarNoBracket() {
        return varNoBracket;
    }

    public void setVarNoBracket(String varNoBracket) {
        this.varNoBracket=varNoBracket;
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
        buffer.append("VarDeclNo(\n");

        buffer.append(" "+tab+varNoBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclNo]");
        return buffer.toString();
    }
}
