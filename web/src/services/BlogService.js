import axios from  'axios'

class BlogService {

    baseUrl='http://localhost:8081/api';

    getPostsForBlogSpace(blogSpaceId){
        return axios.get(this.baseUrl+'/allPostsByBlogSpace/'+blogSpaceId);
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

    addNewPost(title, description, content, userName,blogSpaceId){
        let data={
            'title':title,
            'description':description,
            'content':content,
            'userName':userName,
            'blogSpaceId':blogSpaceId,
            'id':1234
        };

        return axios.post(this.baseUrl+'/addPost', data);
    }


    getAllBlogSpaces(){
        return axios.get(this.baseUrl+'/allBlogSpaces')
    }

    addNewSpace(spaceName, description, theme, userName){
        let data={
            'spaceName':spaceName,
            'description':description,
            'theme':theme,
            'userName':userName
        }
        return axios.post(this.baseUrl+'/addNewBlogSpace',data);
    }
}

export default new BlogService();