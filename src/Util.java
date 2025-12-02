public class Util {
    public static String buatId(String kategori, int nomorUrut) {
        if (nomorUrut < 10) 
            kategori+="000" + nomorUrut;
        else if (nomorUrut < 100) 
            kategori+="00" + nomorUrut;
        else if (nomorUrut < 1000)
            kategori+= "0" + nomorUrut;
        return kategori;
    }
}

