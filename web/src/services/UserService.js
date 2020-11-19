import axios from 'axios'

class UserService {

    baseUrl='http://localhost:8081/api';

    getUsers(){
        return axios.get('http://localhost:8081/api/users')
    }

    signInUser(userName, password){
        return axios.get(this.baseUrl+'/signIn/'+userName +'/'+password);
    }

    createUserAccount(name, userName, password){
        let data={
            'name':name,
            'userName':userName,
            'password':password
        };
        return axios.post(this.baseUrl+'/signUp', data);
    }
}

export default new UserService();