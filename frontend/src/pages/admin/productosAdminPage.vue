<template>
<v-container>
   <v-sheet class="ma-5">
      <productoTabla 
      :initialize="initialize"/>
   </v-sheet>

</v-container>

</template>

<script setup>
import { ref } from 'vue'; 
import productoTabla from '@/components/admin/productoTabla.vue';
const initialize = ref(false);
</script>