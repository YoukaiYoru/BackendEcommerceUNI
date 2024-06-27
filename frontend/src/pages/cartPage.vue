<template>

   <v-container>
      <v-row class="my-3">
            <v-btn to="/productos" icon="mdi-chevron-left" class="text-white bg-yellow-darken-2" ></v-btn>       
            <p class="my-auto ml-3">Volver</p>
      </v-row>
         <v-row>
            <v-col>
               <!-- !TODO Agregar botones para aumentar cantidad  -->
               <v-container>
                  <v-card-title> Carrito</v-card-title>
                  <v-container>
                  <itemsCart v-for="product in productos" 
                  :key="product.id" 
                  :name="product.nombre"
                  :price="product.precio"
                  :img="product.imagen"
                  :quantity="product.quantity"
                  :deleteProduct="eliminarProducto"
                  class="ma-2 pa-2"
                  />
                  </v-container>
               </v-container>
            </v-col>
            <v-col>

               <!--!TODO Resumen de compra y venta - Falta cambiar los valores -->

               <v-container class="ma-2 pa-2">
                  <v-card-title> Resumen</v-card-title>
                  <v-card class="ma-5 pa-5  bg-purple-lighten-3">
                     <v-row class="ma-5">
                        <v-card-text>Subtotal</v-card-text>
                        <v-card-text> $1</v-card-text>                        
                     </v-row>
                     <v-row class="ma-5">
                        <v-card-text>Descuento</v-card-text>
                        <v-card-text> $1</v-card-text>
                     </v-row>
                     <v-divider class="mx-5 border-opacity-100"
                     color="white"
                     :thickness="3"></v-divider>
                     <v-row class="ma-5 pa-2">
                        <v-card-text>total</v-card-text>
                        <v-card-text>$4</v-card-text>
                     </v-row>

                     <v-btn block class="bg-yellow-darken-2 text-white">Pagar</v-btn>
                  </v-card>

               </v-container>
            </v-col>
         </v-row>
   </v-container>
   
</template>

<script setup>

import itemsCart from '@/components/itemsCart.vue';
import { useProductosStore } from '@/stores/productosStore';

const store = useProductosStore();
const productos = store.productosCarrito;
const eliminarProducto = (id) => {
   store.eliminarProducto(id);
}
console.log(productos)


</script>