<template>
   <v-container>
      <v-sheet class="ma-5">
         <usuariosTabla :initialize="initialize"/> 
      </v-sheet>
   </v-container>
</template>

<script setup>
import { ref } from 'vue';
import usuariosTabla from '@/components/admin/usuariosTabla.vue';
const initialize = ref(false);

</script>