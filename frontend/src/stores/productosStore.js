import axios from "axios";
import { defineStore } from "pinia";

export const useProductosStore = defineStore('productosStore',{
   state: () => ({
      listaProductos: [],
      productosCarrito: [],
      searchTerm: '',
   }),

   getters: { 
      getProductos(state){
         return state.listaProductos;
      },
      getCarrito(state){
         return state.productosCarrito;
      }


   },

   actions: {
      async fetchProductos(){
         try {
            const response = await axios.get('http://localhost:8080/producto/get/productos');
            this.listaProductos = response.data;
         } catch (error) {
            alert('Error al obtener los productos');
            console.error(error);
         }
      },

      agregarProducto(item){
         this.productosCarrito.push(item);
         console.log(item);
      },
      eliminarProducto(item){
         const index = this.productosCarrito.findIndex(producto => producto.id === item.id);
         if (index !== -1) {
         this.productosCarrito.splice(index, 1);
         }
      },
      async fetchProducto(id) {
         try {
            this.loading = true;
            const response = await axios.get(`http://localhost:8080/producto/id/${id}`);
            this.producto = response.data;
            } catch (error) {
            console.error('Error al obtener el producto:', error);
            this.error = error.message;
            } finally {
            this.loading = false;
            }
         },
   } 

});