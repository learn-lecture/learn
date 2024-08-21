import View from "./View.js";
import {qs} from "../global/helpers.js";

export default class UserProfileView extends View {

    constructor() {
        super(qs("#profile"));

        this.template = new Template();
    }

    show(data = null) {
        data === null ?
            this.element.innerHTML = this. template.getEmptyMessage() :
            this.element.innerHTML = this.template.getUserProfile(data);

        super.show();
    }

}

class Template {

    getUserProfile(data) {
        return `
            <div class="card card-body">
                <div class="row">
                    <div class="col-md-3 col-sm-12 text-center text-md-start px-4 px-md-3">
                        <img class="img-fluid mb-3 w-100" src="${data.avatar_url}" alt="${data.login}">
                        <a href="${data.html_url}" class="btn mb-4 profile-btn w-100" target="_blank" rel="noopener noreferrer">View
                            Profile</a>
                    </div>
                    <div class="col-md-9 col-sm-12">
                        <div class="d-flex flex-wrap justify-content-center justify-content-md-start mb-3">
                            <span class="badge p-2 m-1 repo">Public Repos: ${data.public_repos}</span>
                            <span class="badge p-2 m-1 gists">Public Gists: ${data.public_gists}</span>
                            <span class="badge p-2 m-1 followers">Followers: ${data.followers}</span>
                            <span class="badge p-2 m-1 following">Following: ${data.following}</span>
                        </div>
                        <ul class="list-group">
                            <li class="list-group-item">Company: ${data.company}</li>
                            <li class="list-group-item">WebSite/Blog: ${data.blog}</li>
                            <li class="list-group-item">Location: ${data.location}</li>
                            <li class="list-group-item">Member Since: ${data.created_at}</li>
                        </ul>
                    </div>
                </div>
            </div>
        `;
    }

    getEmptyMessage() {
        return `
            <div class="card card-body text-center empty-container">
                <h2>
                    검색되는 사용자가 없습니다.
                </h2>
            </div>
        `;
    }

}