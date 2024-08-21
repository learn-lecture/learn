export default class UserRepo {

    constructor(data) {
        this.id = data.id;
        this.htmlUrl = data.html_url;
        this.stargazersCount = data.stargazers_count;
        this.watchers = data.watchers;
        this.forks = data.forks;
    }

}
