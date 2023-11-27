<template>
  <div class="confirmEmail">{{ confirmedMsg }}</div>
</template>

<script>
import {api} from "@/api";

export default {
  name: "ConfirmEmail",

  data() {
    return {
      confirmedMsg: ''
    }
  },
  created() {
    this.confirmEmail();
  },
  methods: {
    confirmEmail() {
      // const urlSections = this.$route.fullPath.split("/");
      // console.log(urlSections)
      let id = this.$route.params.id
      api.get("user/verify/" + id).then(r => {
            if (r.status === 200) {
              this.confirmedMsg = "Email address successfully verified."
            }
          },
          err => {
            if (err.response.status === 409) {
              this.confirmedMsg = "This email address has been already verified."
            } else {
              this.confirmedMsg = "Unable to confirm email address."
            }
          });
    }
  }
}
</script>