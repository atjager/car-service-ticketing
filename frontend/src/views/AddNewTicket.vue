<template>
  <div>
    <!--    <v-alert-->
    <!--        type="warning"-->
    <!--        v-show="isWarning"-->
    <!--    >{{ warningMsg }}</v-alert>-->
    <v-row>
      <div class="col-md-6">
        <v-form
            ref="form"
            v-model="formModel"
        >
          <v-text-field
              v-model="name"
              label="Name"
              required
              style="max-width: 300px"
              :rules="fieldRules"
          ></v-text-field>

          <v-textarea
              v-model="description"
              label="Description"
              required
              style="max-width: 300px"
              :rules="fieldRules"
          ></v-textarea>

          <v-select
              v-model="selectedCustomer"
              :items="customers"
              label="Customer"
              style="max-width: 300px"
          ></v-select>
          <v-select
              v-model="selectedUser"
              :items="users"
              label="User"
              style="max-width: 300px"
          ></v-select>
          <v-select
              v-model="selectedStatus"
              :items="statuses"
              label="Status"
              style="max-width: 300px"
          ></v-select>


          <v-btn @click="saveTicket" color="blue" dark>Save</v-btn>
          <v-btn style="margin-left: 20px" color="grey" @click="$router.push('/tickets')" dark>Cancel
          </v-btn>

        </v-form>
      </div>

    </v-row>
  </div>
</template>

<script>
import {api} from "@/api";
import VueCookies from "vue-cookies";


export default {
  name: "AddNewTicket",
  data() {
    return {
      customers: [],
      users: [],
      selectedCustomer: '',
      selectedUser: '',
      name: '',
      description: '',
      statuses: ["CREATED", "IN_PROGRESS", "TODO", "BLOCKED", "DONE"],
      selectedStatus: '',
      formModel: '',
    }
  },
  computed: {
    fieldRules() {
      const rules = []
      const rule = v => !!v || 'This field is required'
      rules.push(rule)
      return rules
    },

  },
  created() {
    this.init()
  },
  methods: {
    validateField() {
      this.$refs.form.validate()
    },
    init() {
      this.getCustomers();
      this.getUsers();
    },
    getCustomers() {
      api.get("customer/all", {}).then(r => {
            for (let i = 0; i < r.data.length; i++) {
              this.customers.push(r.data[i].name)
            }
          },
      );
    },
    getUsers() {
      api.get("user/all", {}).then(r => {
            for (let i = 0; i < r.data.length; i++) {
              this.users.push(r.data[i].username)
            }
          },
      );
    },

    saveTicket() {
      this.emailRuleBool = true
      this.$refs.form.validate()
      let route = '';
      if (this.name && this.description && this.selectedCustomer && this.selectedStatus) {
        if(this.selectedUser === '') {
          route = '/ticket/create';
          this.selectedUser = null;
        } else {
          route = '/ticket/create/' + this.selectedUser;
        }
        api.post(route, {
          name: this.name,
          description: this.description,
          status: this.selectedStatus,
          customerId: this.selectedCustomer,
          user: null
        }).then(r => {
          if (r.status == 200) {

            VueCookies.set("success", true, "5s");
            this.$router.push("/tickets")
          }
        }, err => {
          console.log("An error occured")
        });
      }

    },
  }
}

</script>

<style scoped>

</style>