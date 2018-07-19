# Day in the Life Agile Integration Workshop

This workshop introduces participants to Agile Integration through a presentation and hands-on lab format. 


## Agenda

* TBD

### Publishing slides

`npm start` is a great way to work locally -- you can create content/slides locally, switch to a web browser, and have the new slides automatically generated. When you're ready to "publish" the slides, you should commit to your `master` branch and then merge those changes into your `gh-pages` branch. This `gh-pages` branch obviously must be set up beforehand, and should start off being a copy of your master branch. Do the following to create your `gh-pages` branch:

```
$  git checkout -b gh-pages
$  git push origin gh-pages
```

This special `gh-pages` branch uses [GitHub pages](https://pages.github.com) to host your slides. Now you can navigate to your project's github pages site and view the published slides. For example, for this template site, you can navigate to [https://redhatworkshops.github.io/workshop-template/slides/](https://redhatworkshops.github.io/workshop-template/slides/) and see the published slides. 

You can work locally, committing on master, and then publish by rebasing/merging to the `gh-pages` branch. 


## Labs

It's best to create labs in some kind of text format. [Gitbook](https://github.com/GitbookIO/gitbook/blob/master/docs/setup.md) is a good option. [Asciidoc](http://asciidoc.org) is another good one. 