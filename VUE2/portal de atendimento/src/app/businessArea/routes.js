import Main from './components/BusinessArea.vue'
import List from './components/BusinessAreaList.vue'

export default [
    {
        path: '/businessArea', component: Main,
        children: [
            {path: '', component: List, name: 'BusinessAreaList'}
        ]
    }
]