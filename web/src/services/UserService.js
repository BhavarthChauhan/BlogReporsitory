import axios from 'axios'

class UserService {
    getUsers(){
        return axios.get('http://localhost:8081/api/users')
    }
}

export default new UserService();