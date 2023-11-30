// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class BrackListOne extends BracketList {

    private BracketList BracketList;
    private VarDeclL VarDeclL;

    public BrackListOne (BracketList BracketList, VarDeclL VarDeclL) {
        this.BracketList=BracketList;
        if(BracketList!=null) BracketList.setParent(this);
        this.VarDeclL=VarDeclL;
        if(VarDeclL!=null) VarDeclL.setParent(this);
    }

    public BracketList getBracketList() {
        return BracketList;
    }

    public void setBracketList(BracketList BracketList) {
        this.BracketList=BracketList;
    }

    public VarDeclL getVarDeclL() {
        return VarDeclL;
    }

    public void setVarDeclL(VarDeclL VarDeclL) {
        this.VarDeclL=VarDeclL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BracketList!=null) BracketList.accept(visitor);
        if(VarDeclL!=null) VarDeclL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BracketList!=null) BracketList.traverseTopDown(visitor);
        if(VarDeclL!=null) VarDeclL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BracketList!=null) BracketList.traverseBottomUp(visitor);
        if(VarDeclL!=null) VarDeclL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BrackListOne(\n");

        if(BracketList!=null)
            buffer.append(BracketList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclL!=null)
            buffer.append(VarDeclL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BrackListOne]");
        return buffer.toString();
    }
}
