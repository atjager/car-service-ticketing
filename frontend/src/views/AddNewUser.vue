<template>
  <div>
    <v-alert
        type="warning"
        v-show="isWarning"
    >{{ warningMsg }}
    </v-alert>
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

          <v-text-field
              v-model="username"
              label="User name"
              required
              style="max-width: 300px"
              :rules="fieldRules"
          ></v-text-field>

          <v-text-field
              v-model="email"
              label="Email"
              required
              type="email"
              :rules="emailRules"
              style="max-width: 300px"
          ></v-text-field>

          <v-select
              v-model="selectedRole"
              :items="roles"
              label="Role of the user"
              style="max-width: 300px"
          ></v-select>


          <v-btn @click="saveUser" color="blue" dark>Save</v-btn>
          <v-btn style="margin-left: 20px" color="grey" @click="$router.push('/users')" dark>Cancel
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

  name: "AddNewUser",
  data() {
    return {
      roles: ["ADMIN", "MANAGER", "MECHANIC"],
      selectedRole: '',
      username: '',
      name: '',
      email: '',
      formModel: '',
      emailRuleBool: false,
      isWarning: false,
      warningMsg: "Username or password already exists",
      update: false,
      id: '',
    }
  },
  computed: {
    emailRules() {
      let rules = []
      if (this.emailRuleBool) {
        rules = [
          v => !v || /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid']
      } else {
        rules = []
      }
      return rules
    },
    fieldRules() {
      const rules = []
      const rule = v => !!v || 'This field is required'
      rules.push(rule)
      return rules
    },

  },
  watch: {
    emailRuleBool: 'validateField',
  },
  methods: {
    validateField() {
      this.$refs.form.validate()
    },

    saveUser() {
      this.emailRuleBool = true
      this.$refs.form.validate()
      if (!this.update) {


        if (this.validateEmail()) {
          if (this.name && this.email && this.selectedRole && this.username) {
            api.post('/user/create', {
              name: this.name,
              email: this.email,
              role: this.selectedRole,
              username: this.username,
            }).then(r => {
              if (r.status == 200) {

                VueCookies.set("success-create", true, "5s");
                this.$router.push("/users")
              }
            }, err => {
              if (err.response.status == 409) {
                this.userExists()
                //this.userNameRuleBool = true;
                setTimeout(() =>
                    this.$refs.form.validate(), 200)
              }
            });
          }
        }
      } else {
        if (this.validateEmail()) {
          if (this.name && this.email && this.selectedRole && this.username) {
            api.put('/user/update', {
              id: this.id,
              name: this.name,
              email: this.email,
              role: this.selectedRole,
              username: this.username,
            }).then(r => {
              if (r.status == 200) {
                VueCookies.set("success-update", true, "5s");
                this.$router.push("/users")
              }
            }, err => {
              if (err.response.status == 409) {
                this.userExists()
                //this.userNameRuleBool = true;
                setTimeout(() =>
                    this.$refs.form.validate(), 200)
              }
            });
          }
        }
      }
    },
    init() {
      if (this.$route.params.id) {
        api.get('user/' + this.$route.params.id)
            .then(r => {
              this.email = r.data.email;
              this.name = r.data.name;
              this.selectedRole = r.data.role;
              this.username = r.data.username;
              this.id = r.data.id;
            })
        this.update = true;
      }
    },


    userExists() {
      this.isWarning = true;
      setTimeout(() =>
          this.isWarning = false, 4000)
    },

    validateEmail() {
      return this.email.match(
          /^(([^<>()[\]\\.,;:\s@\\"]+(\.[^<>()[\]\\.,;:\s@\\"]+)*)|(\\".+\\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
    }

  },
  mounted() {
    this.init()
  },


}

</script>

<style scoped>

</style>