package com.example.proyectoandroid.formatter;

public interface Inspector {

    public static boolean contrastarIndice(int i, int[] ids){
        for(int idx : ids){
            if(i==idx)return true;
        }
        return false;
    }
    public static Object[] filtrarIndices(Object[] lista, int[] indices){
        Object[] out = new Object[indices.length];
        for(int i = 0; i < indices.length; i++){
            out[i] = lista[indices[i]];
        }
        return out;
    }
    public static int indiceDe(String tx, String[] k){
        int i = 0;
        for(String coin : k){
            if(tx.equals(coin)) return i;
            i++;
        }
        return -1;
    }
}
