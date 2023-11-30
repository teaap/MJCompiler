// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class FormParamDeclVarNo extends FormParamDeclVar {

    private String nameNoBracket;

    public FormParamDeclVarNo (String nameNoBracket) {
        this.nameNoBracket=nameNoBracket;
    }

    public String getNameNoBracket() {
        return nameNoBracket;
    }

    public void setNameNoBracket(String nameNoBracket) {
        this.nameNoBracket=nameNoBracket;
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
        buffer.append("FormParamDeclVarNo(\n");

        buffer.append(" "+tab+nameNoBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamDeclVarNo]");
        return buffer.toString();
    }
}
