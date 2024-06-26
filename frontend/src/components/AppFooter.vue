<template>
  <v-footer class="bg-red-darken-4 mt-5">
    <v-row justify="center" no-gutters>
      <v-btn
        v-for="link in links"
        :key="link"
        class="mx-2"
        color="white"
        rounded="xl"
        variant="text"
      >
        {{ link }}
      </v-btn>
      <v-col class="text-center mt-4" cols="12">
        {{ new Date().getFullYear() }} — <strong>UniMarket</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script setup>
import { ref } from 'vue'

const links = ref([
  'Términos y condiciones',
  'Políticas de cookies',
  'Políticas de privacidad',
])

</script>

<style scoped>
/* Your component's styles go here */
</style>