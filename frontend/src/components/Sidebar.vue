<template>
  <v-navigation-drawer
      v-model="open"
      absolute
      temporary
      class="blue-grey darken-1"
      dark
  >
    <v-list-item>

      <v-list-item-content>
        <v-list-item-title style="margin-left: 0">Menu</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense>
      <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.url"
      >
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
    <template v-slot:append>
      <div class="pa-2">
        <v-btn block>
          Logout
        </v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "sidebar",
  props: {
    drawer: Boolean
  },
  data() {
    return {
      open: this.drawer,
      items: [
        {title: 'Home', icon: 'mdi-home', url: '/'},
        {title: 'Tickets', icon: 'mdi-ticket-outline', url: '/tickets'},
        {title: 'Customers', icon: 'mdi-card-account-details-outline', url: '/customers'},
        {title: 'Users', icon: 'mdi-account-supervisor', url: '/users'},
        {title: 'Settings', icon: 'mdi-cog', url: '/settings'},
      ],
    }
  },
  watch: {
    drawer(value) {
      this.open = value;
    },
    open(value) {
      if (value == false) {
        this.$emit('close-drawer');
      }
    }
  }
}
</script>

<style scoped>

</style>