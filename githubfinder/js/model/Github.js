let instance = null;

export default class Github {

    constructor() {
        this.login = null;
        this.avatar_url = null;
        this.follower = null;
        this.following = null;
        this.public_repos = null;
        this.public_gists = null;
        this.html_url = null;
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

    parseUserData({ login, avatar_url, html_url, follower, following, public_repos, public_gists }) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
        this.follower = follower;
        this.following = following;
        this.public_repos = public_repos;
        this.public_gists = public_gists;
    }

}