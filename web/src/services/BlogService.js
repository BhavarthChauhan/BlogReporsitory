import axios from  'axios'

class BlogService {

    baseUrl='http://localhost:8081/api';

    getPosts(){
        return axios.get(this.baseUrl+'/allPosts');
    }

    getPostDetails(postId){
        return axios.get(this.baseUrl+'/postDetails/'+postId)
    }

    getCommentsForPost(postId){
        return axios.get(this.baseUrl+'/commentsForPost/'+postId)
    }

    addCommentToPost(post, user, comment){

        let data={
            'userName':user,
            'postId':post,
            'text':comment
        };

        return axios.post(this.baseUrl+'/addComment', data);

    }
}

export default new BlogService();