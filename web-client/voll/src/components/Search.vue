<template>
    <div>
        <input @input="filter($event)" class="field-input" type="text" placeholder="Search" autofocus>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'Search',
    data () {
        return {
            filteredEntities: []
        }
    },
    emits: ['filter', 'list'],
    props: {
        page: {
            type: String
        },
        entities: {
            type: Object
        }
    },
    methods: {
        filter(e) {
            e.preventDefault()

            if (!e.target.value.length) {
                this.$emit('list')
                return
            }

            const info = this.page === 'doctors' ? 'specialty' : 'phone'

            this.filteredEntities = this.entities.filter(entity => {
                return entity.name.toLowerCase().indexOf(e.target.value.toLowerCase()) != -1 ||
                    entity[info].toLowerCase().indexOf(e.target.value.toLowerCase()) != -1;
            })
            this.filteredEntities.forEach(en => {
                en.i = this.filteredEntities.indexOf(en)
            })

            this.$emit('filter', this.filteredEntities)
        }
    }
})
</script>

<style>
.search {
    font-size: 1rem;
    border: 2px solid #0b3b60;
    border-radius: 1rem;
    padding: 5px;
}
</style>