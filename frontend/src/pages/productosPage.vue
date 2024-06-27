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
            v-for="item in paginatedItems"
            :key="item.id_product"
            cols="12" sm="6" md="4" lg="3"
            class="d-flex align-center justify-center"
            >     
            <productosCard
            :nombreProducto="item.product_name"
            :imagenProducto="item.product_img_url"
            :precio="item.product_price"
            @click="goToProducto(item.id_product)"
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
         v-model="page"
         :length="totalPages"
         :total-visible="5"
         @input="store.fetchProductos"
      ></v-pagination>




   </v-container>
</template>

<script setup>
import { ref,onMounted, computed,watch } from 'vue'
import { useProductosStore } from '@/stores/productosStore';
import productosCard from '@/components/productosCard.vue'
import { useRouter } from 'vue-router'


const router = useRouter()

const filtro = ref([
   { id:'1',title: 'Defecto'},
   { id:'2',title: 'Precio más bajo' },
   { id:'3',title: 'Precio más alto' },
   { id:'4',title: 'Más vendidos' },
   { id:'5',title: 'Mejor valorados' },
])

const store = useProductosStore()
const items = computed(() => {
   return store.getProductos
})

const goToProducto = (id) => {
   router.push({name:'productoDetalle', params: { id }})
}


const orden = ref()
const error = ref(null);
const loading = ref(false);
const page = ref(1);
const pageSize = 12; // Tamaño de página, puede ser ajustado según tus necesidades

const totalPages = computed(() => Math.ceil(items.value.length / pageSize));
const paginatedItems = computed(() => {
   const start = (page.value - 1) * pageSize;
   const end = start + pageSize;
   return items.value.slice(start, end);
});

onMounted(
   () => {
      store.fetchProductos();
   }
)


watch(items, () => {
  page.value = 1; // Reiniciar a la primera página cuando cambian los productos
});
</script>