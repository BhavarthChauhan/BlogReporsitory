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

    addNewPost(title, description, content, userName){
        let data={
            'title':title,
            'description':description,
            'content':content,
            'userName':userName
        };

        return axios.post(this.baseUrl+'/addPost', data);
    }
}

export default new BlogService();