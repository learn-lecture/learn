import View from "./View.js";
import {qs} from "../global/helpers.js";

export default class UserReposView extends View {

    constructor() {
        super(qs("#repos"));

        this.template = new Template();
    }

    show(data = []) {
        console.log(data);
        if (data.length > 0) {
            this.element.innerHTML = this.template.getUserRepos(data);
        } else {
            this.element.innerHTML = "보유중인 레파지토리가 없습니다.";
        }
        super.show();
    }

}

class Template {

    getUserRepos(data) {
        return `
            <h2 class="mb-3">Latest Repos</h2>
            <div class="list-group">
                ${data.map(this.getItem).join("")}
            </div>
        `;
    }

    getItem({ name, htmlUrl, stargazersCount, watchers, forks }) {
        return `
            <div class="list-group-item">
                <div class="repo-info">
                    <div class="repo-header">
                        <h5 class="mb-1 repo-name">
                            <a href="${htmlUrl}" class="text-primary text-decoration-none" title="${name}">${name}</a>
                        </h5>
                        <div class="repo-stats">
                            <span class="badge p-2 stars mt-2">Stars: ${stargazersCount}</span>
                            <span class="badge p-2 watchers mt-2">Watchers: ${watchers}</span>
                            <span class="badge p-2 forks mt-2">Forks: ${forks}</span>
                        </div>
                    </div>
                    <div class="repo-stats-mobile">
                        <span class="badge p-2 stars">Stars: ${stargazersCount}</span>
                        <span class="badge p-2 watchers">Watchers: ${watchers}</span>
                        <span class="badge p-2 forks">Forks: ${forks}</span>
                    </div>
                </div>
            </div>
        `;
    }

}