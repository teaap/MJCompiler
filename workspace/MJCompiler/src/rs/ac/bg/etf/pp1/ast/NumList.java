// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class NumList extends NumCharBoolList {

    private NumCharBoolList NumCharBoolList;
    private NumCharBool NumCharBool;

    public NumList (NumCharBoolList NumCharBoolList, NumCharBool NumCharBool) {
        this.NumCharBoolList=NumCharBoolList;
        if(NumCharBoolList!=null) NumCharBoolList.setParent(this);
        this.NumCharBool=NumCharBool;
        if(NumCharBool!=null) NumCharBool.setParent(this);
    }

    public NumCharBoolList getNumCharBoolList() {
        return NumCharBoolList;
    }

    public void setNumCharBoolList(NumCharBoolList NumCharBoolList) {
        this.NumCharBoolList=NumCharBoolList;
    }

    public NumCharBool getNumCharBool() {
        return NumCharBool;
    }

    public void setNumCharBool(NumCharBool NumCharBool) {
        this.NumCharBool=NumCharBool;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NumCharBoolList!=null) NumCharBoolList.accept(visitor);
        if(NumCharBool!=null) NumCharBool.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NumCharBoolList!=null) NumCharBoolList.traverseTopDown(visitor);
        if(NumCharBool!=null) NumCharBool.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NumCharBoolList!=null) NumCharBoolList.traverseBottomUp(visitor);
        if(NumCharBool!=null) NumCharBool.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NumList(\n");

        if(NumCharBoolList!=null)
            buffer.append(NumCharBoolList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumCharBool!=null)
            buffer.append(NumCharBool.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumList]");
        return buffer.toString();
    }
}
