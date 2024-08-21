let instance = null;

export default class Github {

    constructor() {
        this.login = null;
        this.avatar_url = null;
        this.followers = null;
        this.following = null;
        this.public_repos = null;
        this.public_gists = null;
        this.html_url = null;
        this.company = null;
        this.blog = null;
        this.location = null;
        this.created_at = null;
    }

    async getUser(nickname) {
        const response = await fetch(`https://api.github.com/users/${nickname}`);
        if (response.status === 404) {
            return false;
        }

        const data = await response.json();
        this.parseUserData(data);
        return true;
    }

    parseUserData(data) {
        this.login = data.login;
        this.avatar_url = data.avatar_url;
        this.html_url = data.html_url;
        this.followers = data.followers;
        this.following = data.following;
        this.public_repos = data.public_repos;
        this.public_gists = data.public_gists;
        this.company = data.company;
        this.blog = data.blog;
        this.location = data.location;
        this.created_at = data.created_at;
    }

}