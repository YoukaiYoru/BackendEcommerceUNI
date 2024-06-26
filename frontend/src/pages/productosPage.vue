<template>
   <v-container class="mt-5">
      <v-row>
         <v-subtitle-card> Mostrando resultados para {{ categoria }}</v-subtitle-card>
      </v-row>
      <div class="d-flex justify-end align-end align-center">
         <p class="mx-5 text-red-darken-4 font-weight-bold">Ordenar por: </p>
         <v-select 
         class="border-solid" 
         
         hide-details
         variant="solo" 
         v-model="orden" 
         :items="filtro" 
         max-width="200"
         density="compact"
         ></v-select>
      </div>

      <v-container>
         <v-row>
            <v-col
            v-for="item in items"
            :key="item.id"
            cols="12" sm="6" md="4" lg="3"
            class="d-flex align-center justify-center"
            >     
            <productosCard
            :nombreProducto="item.title"
            :imagenProducto="item.image"
            :precio="item.price"
            />
            </v-col>
         </v-row>
      </v-container>

      <v-row v-if="error">
         <v-col>
            <v-alert type="error">{{ error }}</v-alert>
         </v-col>
      </v-row>
         <v-row v-if="loading">
            <v-col class="d-flex justify-center">
               <v-progress-circular indeterminate></v-progress-circular>
            </v-col>
      </v-row>

      <v-pagination
         v-model="currentPage"
         :length="15"
         :total-visible="5"
         @input="fetchData"
      ></v-pagination>




   </v-container>
</template>

<script setup>

import { ref,onMounted,getCurrentInstance } from 'vue'
import productosCard from '@/components/productosCard.vue'

const categoria = 'Cálculo Diferencial'

const { proxy } = getCurrentInstance()

const filtro = ref([
   { id:'1',title: 'Defecto'},
   { id:'2',title: 'Precio más bajo' },
   { id:'3',title: 'Precio más alto' },
   { id:'4',title: 'Más vendidos' },
   { id:'5',title: 'Mejor valorados' },
])

const orden = ref() 

const items = ref(null)
const currentPage = ref(1);
const error = ref(null);
const loading = ref(false);

const fetchData = async() =>{
   loading.value = true
   try{
      const response = await proxy.$axios.get('products')
      items.value = response.data
      console.log(items.value)
   } catch (err){
      console.log(err)
   } finally {
      loading.value = false
   }
}


onMounted(fetchData)



   
</script>