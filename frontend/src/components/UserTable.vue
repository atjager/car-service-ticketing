<template>
  <v-data-table
      :headers="headers"
      :items="users"
      sort-by="name"
      class="elevation-1"
      ref="table"
      height="300"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Users</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <template v-slot:activator="{  }">
            <v-btn
                color="teal"
                dark
                class="mb-2"
                @click="$router.push('/add-new-user')"
            >
              New user
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>

    <template v-slot:item.created="{ item }">
      <p>{{ getTimestamp(item.created) }}</p>
    </template>
    <template v-slot:item.actions="{ item }">
            <v-icon
                small
                class="mr-2"
                @click="editItem(item)"
                :disabled="isAdmin(item)"
            >
              mdi-pencil
            </v-icon>
      <v-icon
          small
          @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <div>No data available</div>
    </template>
  </v-data-table>
</template>

<script>
import {api} from "@/api";

export default {
  name: "UserTable",
  data: () => ({
    idToDelete: '',
    dialogDelete: false,
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {text: 'Username', value: 'username'},
      {text: 'Role', value: 'role'},
      {text: 'Email', value: 'email'},
      {text: 'Created', value: 'created'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    users: [],
  }),

  computed: {
  },

  watch: {
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },

  methods: {
    initialize() {
      this.getUsers();
    },
    getTimestamp(iso8601) {
      return new Date(iso8601).toLocaleDateString();
    },
    getUsers() {
      api.get("user/all", {
      }).then(r => {
            this.users = r.data;
          },
      );

    },

    deleteItem(item) {
      this.editedIndex = this.users.indexOf(item)
      this.dialogDelete = true
      this.idToDelete = item.id;
    },

    deleteItemConfirm() {
      api.delete("/user/delete?id=" + this.idToDelete)
      //this.users.splice(this.editedIndex, 1)
      this.idToDelete = '',
          this.closeDelete()

    },


    closeDelete() {
      this.idToDelete = '',
          this.dialogDelete = false
      this.$nextTick(() => {
        this.editedIndex = -1
      })
      setTimeout(this.getUsers(), 500);


    },
    isAdmin(item) {
      if (item.userName == "admin") {
        return true;
      } else {
        return false;
      }
    }
  },
}
</script>

<style scoped>

</style>