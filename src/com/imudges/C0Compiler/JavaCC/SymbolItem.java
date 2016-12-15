
/**
 * @author shianqi@imudges.com
 * Created by shianqi on 2016/12/13
 */
public class SymbolItem {
    public SymbolItem(SymbolType type) {
        this.type = type;
        this.name = "";
        this.val = 0;
        this.level = 0;
        this.adr = 0;
        this.size = 0;
        this.returnType = 0;
    }

    public String toString(){
        return type.toString()+"\t"+name+"\t"+adr+"\t"+returnType;
    }

    public enum SymbolType{
        intSym,
        functionSym
    }

    private String name;
    private SymbolType type;
    private int val;
    private int level;
    private int adr;
    private int size;
    private int returnType;

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAdr() {
        return adr;
    }

    public void setAdr(int adr) {
        this.adr = adr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
