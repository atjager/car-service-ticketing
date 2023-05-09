<template>
  <div>
    <v-alert
        type="warning"
        v-show="isWarning"
    >{{ warningMsg }}</v-alert>
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
              v-model="email"
              label="Email"
              required
              type="email"
              :rules="emailRules"
              style="max-width: 300px"
          ></v-text-field>
          <v-text-field
              v-model="phone"
              label="Phone number"
              required
              style="max-width: 300px"
              :rules="phoneRules"
          ></v-text-field>

          <v-btn @click="saveCustomer" color="blue" dark>Save</v-btn>
          <v-btn style="margin-left: 20px" color="grey" @click="$router.push('/customers')" dark>Cancel
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

  name: "AddNewCustomer",
  data() {
    return {
      phone: '',
      name: '',
      email: '',
      formModel: '',
      emailRuleBool: false,
      phoneRuleBool: false,
      isWarning: false,
      warningMsg: "Name or email already exists"
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
    phoneRules() {
      let rules = []
      if (this.phoneRuleBool) {
        rules = [
          v => !v || /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(v) || 'Phone number must be valid']
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

    saveCustomer() {
      this.emailRuleBool = true
      this.phoneRuleBool = true
      this.$refs.form.validate()
      if (this.validateEmail() && this.validatePhone()) {

        if (this.name && this.email)
          api.post('/customer/create', {
            name: this.name,
            email: this.email,
            phone: this.phone,
          }).then(r => {
            if (r.status == 200) {

              VueCookies.set("success", true, "5s");
              this.$router.push("/customers")
            }
          }, err => {
            if (err.response.status == 409) {
              this.customerExists()
              setTimeout(() =>
                  this.$refs.form.validate(), 200)
            }
          });
      }
    },
    init() {
    },


    customerExists() {
      this.isWarning = true;
      setTimeout(() =>
          this.isWarning = false, 4000)
    },

    validateEmail() {
      return this.email.match(
          /^(([^<>()[\]\\.,;:\s@\\"]+(\.[^<>()[\]\\.,;:\s@\\"]+)*)|(\\".+\\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
    },

    validatePhone() {
      return this.phone.match(
          /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/
      )
    }

  },
  mounted() {
    this.init()
  },


}

</script>

<style scoped>

</style>