export default class UserProfile {

    constructor(data) {
        this.login = data.login;
        this.avatarUrl = data.avatar_url;
        this.htmlUrl = data.html_url;
        this.followers = data.followers;
        this.following = data.following;
        this.publicRepos = data.public_repos;
        this.publicGists = data.public_gists;
        this.company = data.company;
        this.blog = data.blog;
        this.location = data.location;
        this.createdAt = data.created_at;
    }

}