public class Denda {
    private String id;
    private Pengembalian pg;
    private ItemPengembalian ipg;
    private int nominal;
    private static int nomorUrut;

    public Denda(Pengembalian pg, ItemPengembalian ipg, int nominal){
        this.pg = pg;
        this.ipg = ipg;
        this.nominal = nominal;
        nomorUrut++;
        this.id = Util.buatId("DND", nomorUrut);

    }
    public String getId() {
        return id;
    }
    public Pengembalian getPengembalian() {
        return pg;
    }
    public ItemPengembalian getipg() {
        return ipg;
    }
    public int getNominal() {
        return nominal;
    }
    
}