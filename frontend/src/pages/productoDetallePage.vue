<template>
   <div v-if="productoSeleccionado" class="ma-5">
      <v-row class="my-3">
            <v-btn to="/productos" icon="mdi-chevron-left" class="text-white bg-yellow-darken-2" ></v-btn>       
            <p class="my-auto ml-3">Volver</p>
      </v-row>
      <v-card>
         <v-row no-gutters>
            <v-col>
            <v-card-title>{{ productoSeleccionado.product_name }}</v-card-title>
            <v-img :src="productoSeleccionado.product_img_url" class="h-50 w-50 ma-5"></v-img>
         </v-col>
         <v-col>
            <p>{{ productoSeleccionado.product_description }}</p>
            <p>Price: s/. {{ productoSeleccionado.product_price }}</p>
            <v-btn @click="addToCart">Add to Cart</v-btn>
         </v-col>
         </v-row>
      </v-card>
   </div>
   <div v-else-if="loading">
   <p>Cargando producto...</p>
   </div>
   <div v-else>
   <p>{{ error }}</p>
   </div>
</template>

<script setup>

import { computed } from 'vue';
import { useProductosStore } from '@/stores/productosStore';
import { useRoute } from 'vue-router';

const store = useProductosStore();
const route = useRoute();
const productoSeleccionado = computed(()=> {
   return store.getProductos.find((producto) => producto.id_product === Number(route.params.id));
})

</script>

<style scoped>

</style>