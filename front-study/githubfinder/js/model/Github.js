import UserRepo from "./UserRepo.js";
import UserProfile from "./UserProfile.js";

export default class Github {

    constructor() {
        this.profile = null;
        this.repos = [];
    }

    async getUser(nickname) {
        const hasProfile = await this.getProfile(nickname);
        const hasRepos = await this.getUserRepo(nickname);

        return hasRepos && hasRepos;
    }

    async getProfile(nickname) {
        const response = await fetch(`https://api.github.com/users/${nickname}`);
        if (response.status === 404) {
            return false;
        }

        const data = await response.json();
        this.profile = new UserProfile(data);
        return true;
    }

    async getUserRepo(nickname) {
        const response = await fetch(`https://api.github.com/users/${nickname}/repos`);
        if (response.status === 404) {
            return false;
        }

        const data = await response.json();
        this.repos = data
            .map(repo => new UserRepo(repo))
            .sort((a, b) => b.id - a.id);

        return true;
    }

}