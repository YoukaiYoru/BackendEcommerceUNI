import axios from "axios";
import { defineStore } from "pinia";

export const useProductosStore = defineStore('productosStore',{
   state: () => ({
      listaProductos: [],
      productosCarrito: []
   }),

   getters: { 
      getProductos(state){
         return state.listaProductos;
      }
   },

   actions: {
      async fetchProductos(){
         try {
            const response = await axios.get('https://fakestoreapi.com/products');
            this.listaProductos = response.data;
         } catch (error) {
            alert('Error al obtener los productos');
            console.error(error);
         }
      },

      agregarProducto(producto){
         this.productosCarrito.push(producto);
         console.log("Agregado al carrito")
      }

   } 

});