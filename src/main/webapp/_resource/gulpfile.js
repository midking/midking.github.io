'use strict';

var gulp = require('gulp');
var includer = require('gulp-html-ssi'),
	sass = require('gulp-sass'),
	sourcemaps = require('gulp-sourcemaps'),
	spritesmith = require('gulp.spritesmith');

/* include */
gulp.task('htmlSSI', function() {
	gulp.src('./html/**/*.html')
		.pipe(includer() )
		.pipe(gulp.dest('./dist/') );
});
gulp.task('htmlSSI:watch', function(){
	gulp.watch('./html/**/*.html', ['htmlSSI']);
});

/* sass & map */
gulp.task('sass', function () {
	return gulp.src('./scss/style.scss')
		.pipe(sourcemaps.init())
		.pipe(sass.sync({outputStyle: 'compact'}).on('error', sass.logError))
		.pipe(sourcemaps.write('./'))
		.pipe(gulp.dest('./css'));
});
gulp.task('sass:watch', function(){
	gulp.watch('./scss/**/*.scss', ['sass']);
});

/* sprite */
gulp.task('sprite', function(){
	var spriteData = gulp.src('./images/sp_img/*.png')
		.pipe(spritesmith({
			imgName: 'sprite_all.png',
			padding: 4,
			cssName: 'sprite_all.scss',
			imgPath: '../images/sprite/sprite_all.png'
		}));
	spriteData.img.pipe(gulp.dest('./images/sprite'));
	spriteData.css.pipe(gulp.dest('./scss'));
});
gulp.task('sprite:watch', function(){
	gulp.watch('./images/sp_img/*.png', ['sprite']);
});

/* help */
gulp.task('help', function(){
	console.log("");
	console.log(" HELP");
	console.log("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
	console.log("┃$ gulp help         ┃ help                         ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp htmlSSI      ┃ include(html+html=html)      ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp htmlSSI:watch┃ include watch                ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp sass         ┃ sass > css                   ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp sass:watch   ┃ sass > css watch             ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp sprite       ┃ *.png > .png+css             ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp sprite:watch ┃ *.png > .png+css watch       ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp first        ┃ start(include, sass, sprite) ┃");
	console.log("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
	console.log("┃$ gulp watch        ┃ watch(include, sass, sprite) ┃");
	console.log("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
	console.log("");
});

/* default */
gulp.task('default', ['help']);

/* first */
gulp.task('first', ['htmlSSI', 'sass', 'sprite']);

/* watch */
gulp.task('watch', function() {
	gulp.start(['first']);
	gulp.watch(['./html/**/*.html'], ['htmlSSI'])
	gulp.watch(['./scss/**/*.scss'], ['sass'])
	gulp.watch(['./images/sp_img/*.png'], ['sprite'])
});