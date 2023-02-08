<template>
    <div>
        <Header />
        <main class="container">
            <form class="form">
                <Logo Class="logo-huge" />

                <Title v-if="!editing" text="Registration form" Class="title" />
                <Title v-else text="Edit" Class="title" />

                <div v-if="!editing" class="form-div block">
                    <h3 class="title">Choose the form type:</h3>
                    <Fieldset>
                        <input type="radio" id="doctor" name="type" value="doctors" v-model="typeIs" required />
                        <label class="radio-label" for="doctor">Doctor</label>
                    </Fieldset>
                    <Fieldset>
                        <input type="radio" id="patient" name="type" value="patients" v-model="typeIs" />
                        <label class="radio-label" for="patient">Patient</label>
                    </Fieldset>
                    <Fieldset>
                        <input type="radio" id="appointment" name="type" value="appointments" v-model="typeIs" />
                        <label class="radio-label" for="appointment">Appointment</label>
                    </Fieldset>
                </div>

                <div class="form-div occupational">
                    <h3 v-if="typeIs === 'doctors'" class="title">Occupational</h3>
                    <h3 v-if="typeIs === 'patients'" class="title">Patient</h3>
                    <div class="form-pair">
                        <Fieldset>
                            <label class="field-label" for="name">Name</label>
                            <input type="text" class="field-input" id="name" v-model="entity.name" required />
                            <span class="error-message"></span>
                        </Fieldset>
                        <Fieldset v-if="typeIs === 'doctors'">
                            <label class="field-label" for="specialty" required>Specialty</label>
                            <select id="specialty" name="specialty" class="field-input" v-model="entity.specialty"
                                required>
                                <option disabled value="">Select a specialty</option>
                                <option value="ANESTHESIOLOGY">ANESTHESIOLOGY</option>
                                <option value="CARDIOLOGY">CARDIOLOGY</option>
                                <option value="DERMATOLOGY">DERMATOLOGY</option>
                                <option value="GYNECOLOGY">GYNECOLOGY</option>
                                <option value="IMMUNOLOGY">IMMUNOLOGY</option>
                                <option value="NEUROLOGY">NEUROLOGY</option>
                                <option value="OPHTHALMOLOGY">OPHTHALMOLOGY</option>
                                <option value="ORTHOPEDICS">ORTHOPEDICS</option>
                                <option value="PATHOLOGY">PATHOLOGY</option>
                                <option value="PEDIATRICS">PEDIATRICS</option>
                                <option value="PSYCHIATRY">PSYCHIATRY</option>
                                <option value="RADIOLOGY">RADIOLOGY</option>
                                <option value="SURGERY">SURGERY</option>
                                <option value="UROLOGY">UROLOGY</option>
                            </select>
                            <span class="error-message"></span>
                        </Fieldset>
                        <Fieldset v-if="typeIs === 'patients'">
                            <label class="field-label" for="ssn">SSN</label>
                            <input type="text" name="SSN" class="field-input" id="ssn" v-model="entity.ssn" v-maska
                                data-maska="###-##-####" minlength="11" maxlength="11" :disabled="editing" required />
                            <span class="error-message"></span>
                        </Fieldset>
                    </div>
                </div>

                <div class="form-div contact">
                    <h3 class="title">Contact</h3>
                    <div class="form-pair">
                        <Fieldset>
                            <label class="field-label" for="email">Email</label>
                            <input type="email" class="field-input" id="email" v-model="entity.email"
                                :disabled="editing" required />
                            <span class="error-message"></span>
                        </Fieldset>
                        <Fieldset>
                            <label class="field-label" for="tel">Phone number</label>
                            <input type="tel" class="field-input" id="tel" v-maska data-maska="(###) ###-####"
                                placeholder="(206) 555-1212" v-model="entity.phone" minlength="14" maxlength="14"
                                required />
                            <span class="error-message"></span>
                        </Fieldset>
                    </div>
                </div>

                <div class="form-div address">
                    <h3 class="title">Professional address</h3>
                    <Fieldset id="zip-field">
                        <label class="field-label" for="zip">Zip code</label>
                        <input type="text" class="field-input" id="zip" v-model="entity.address.postalCode"
                            maxlength="5" required />
                        <span class="error-message"></span>
                    </Fieldset>
                    <div class="form-pair">
                        <Fieldset>
                            <label class="field-label" for="address1">Address line 1</label>
                            <input type="text" class="field-input" id="address1" v-model="entity.address.addressLine1"
                                required />
                            <span class="error-message"></span>
                        </Fieldset>
                        <Fieldset>
                            <label class="field-label" for="address2">Address line 2</label>
                            <input type="text" class="field-input" id="address2"
                                v-model="entity.address.addressLine2" />
                            <span class="error-message"></span>
                        </Fieldset>
                    </div>
                    <div class="form-pair">
                        <Fieldset>
                            <label class="field-label" for="city">City</label>
                            <input type="text" class="field-input" id="city" v-model="entity.address.city" required />
                            <span class="error-message"></span>
                        </Fieldset>
                        <Fieldset>
                            <label class="field-label" for="state">State</label>
                            <select id="state" name="state" v-model="entity.address.state" class="field-input" required>
                                <option disabled value="">Select a state</option>
                                <option value="AL">Alabama</option>
                                <option value="AK">Alaska</option>
                                <option value="AZ">Arizona</option>
                                <option value="AR">Arkansas</option>
                                <option value="CA">California</option>
                                <option value="CO">Colorado</option>
                                <option value="CT">Connecticut</option>
                                <option value="DE">Delaware</option>
                                <option value="DC">District Of Columbia</option>
                                <option value="FL">Florida</option>
                                <option value="GA">Georgia</option>
                                <option value="HI">Hawaii</option>
                                <option value="ID">Idaho</option>
                                <option value="IL">Illinois</option>
                                <option value="IN">Indiana</option>
                                <option value="IA">Iowa</option>
                                <option value="KS">Kansas</option>
                                <option value="KY">Kentucky</option>
                                <option value="LA">Louisiana</option>
                                <option value="ME">Maine</option>
                                <option value="MD">Maryland</option>
                                <option value="MA">Massachusetts</option>
                                <option value="MI">Michigan</option>
                                <option value="MN">Minnesota</option>
                                <option value="MS">Mississippi</option>
                                <option value="MO">Missouri</option>
                                <option value="MT">Montana</option>
                                <option value="NE">Nebraska</option>
                                <option value="NV">Nevada</option>
                                <option value="NH">New Hampshire</option>
                                <option value="NJ">New Jersey</option>
                                <option value="NM">New Mexico</option>
                                <option value="NY">New York</option>
                                <option value="NC">North Carolina</option>
                                <option value="ND">North Dakota</option>
                                <option value="OH">Ohio</option>
                                <option value="OK">Oklahoma</option>
                                <option value="OR">Oregon</option>
                                <option value="PA">Pennsylvania</option>
                                <option value="RI">Rhode Island</option>
                                <option value="SC">South Carolina</option>
                                <option value="SD">South Dakota</option>
                                <option value="TN">Tennessee</option>
                                <option value="TX">Texas</option>
                                <option value="UT">Utah</option>
                                <option value="VT">Vermont</option>
                                <option value="VA">Virginia</option>
                                <option value="WA">Washington</option>
                                <option value="WV">West Virginia</option>
                                <option value="WI">Wisconsin</option>
                                <option value="WY">Wyoming</option>
                            </select>
                            <span class="error-message"></span>
                        </Fieldset>
                    </div>
                </div>

                <div v-if="!editing">
                    <input @click.prevent="submit($event)" @keyup.enter.prevent="submit($event)"
                        value="Register profile" class="btn form-btn" id="send" type="submit">
                </div>
                <div v-else>
                    <div v-if="typeIs === 'doctors'">
                        <p @click="showModal(entity.id, entity.name, entity.specialty)"
                            @keyup.enter="showModal(entity.id, entity.name, entity.specialty)" tabindex="0"
                            class="deactivate">
                            Deactivate profile
                        </p>
                    </div>
                    <div v-if="typeIs === 'patients'">
                        <p @click="showModal(entity.id, entity.name, entity.ssn)"
                            @keyup.enter="showModal(entity.id, entity.name, entity.ssn)" tabindex="0"
                            class="deactivate">
                            Deactivate profile</p>
                    </div>
                    <Modal ref="modal" />
                    <div>
                        <input @click.prevent="submit($event)" @keyup.enter.prevent="submit($event)"
                            value="Edit profile" class="btn form-btn" id="send" type="submit">
                    </div>
                </div>
                <div>
                    <router-link to="/"><input class="btn-reverse form-btn" value="Cancel"
                            type="button" /></router-link>
                </div>
            </form>
        </main>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Logo from '../components/Logo.vue';
import Title from '../components/Title.vue';
import Fieldset from '@/components/Fieldset.vue';
import Modal from '@/components/Modal.vue';
import axios from 'axios';
import { Mask, vMaska } from 'maska';
import Header from '@/components/Header.vue';
import IDoctor from '@/interfaces/IDoctor';
import IPatient from '@/interfaces/IPatient';
import IAddress from '@/interfaces/IAddress';
import handleBadRequest from '@/utilities/handleBadRequest';

export default defineComponent({
    name: 'FormView',
    directives: {
        maska: vMaska
    },
    components: {
        Logo,
        Title,
        Fieldset,
        Modal,
        Header
    },
    data() {
        return {
            falsy: false,
            editing: false,
            entity: {} as IDoctor || {} as IPatient,
            typeIs: this.$route.params.type as string,
            idIs: this.$route.params.id as string,
            token: sessionStorage.getItem('token'),
            phoneMask: new Mask({ mask: "(###) ###-####" }),
            ssnMask: new Mask({ mask: "###-##-####" })
        }
    },
    methods: {
        showModal(id, name, specialty) {
            (this.$refs.modal as typeof Modal).showModal(id, name, specialty)
        },
        submit(e) {
            e.target.blur()
            const data = {
                ...(this.typeIs === 'patients') && { ssn: this.entity.ssn.replace(/\D/g, '') },
                ...(this.typeIs === 'doctors') && { specialty: this.entity.specialty },
                name: this.entity.name,
                email: this.entity.email,
                phone: this.entity.phone.replace(/\D/g, ''),
                addressData: this.entity.address
            }

            let method = 'post'
            if (this.editing) {
                method = 'put'
            }

            const url = `http://localhost:8081/${this.typeIs}`
            axios[method](url, {
                ...(this.editing) && { id: this.idIs },
                ...data
            }, {
                headers: {
                    Authorization: `Bearer ${this.token}`
                }
            })
                .then(response => {
                    console.log(response)
                    this.$router.push(`/${this.typeIs}`)
                })
                .catch(error => {
                    const code = error.response.status
                    const errors = error.response.data
                    this.handleBadRequest(code, errors)
                })
        },
        handleBadRequest
    },
    created() {
        this.entity.address = {} as IAddress
        this.entity.ssn = ''
        this.entity.phone = ''
        this.entity.address.addressLine2 = ''
        if (this.idIs === 'new') {
            return
        }
        this.editing = true
        const url = `http://localhost:8081/${this.typeIs}/${this.idIs}`
        axios.get(url, {
            headers: {
                Authorization: `Bearer ${this.token}`
            }
        })
            .then(response => {
                this.entity = response.data

                if (this.typeIs === 'patients') {
                    this.entity.ssn = this.ssnMask.masked(this.entity.ssn);
                }

                this.entity.phone = this.phoneMask.masked(this.entity.phone);
            })
            .catch(error => {
                console.log(error)
            })
    }
})

</script>

<style scoped>
.block {
    display: block;
}

.deactivate {
    color: #0b3b60;
    cursor: pointer;
    font-weight: 600;
    text-align: center;
    text-decoration: underline;
}

.form-btn {
    margin-top: 0;
    font-weight: 500;
}

@media screen and (max-width: 900px) {
    .block {
        display: inline;
        margin: 0 auto;
    }
}
</style>