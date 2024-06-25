import { defineStore } from "pinia";

export const useProductosStore = defineStore('productosStore',{
   state: () => ({
      listaProductos: []
   }),
});