var pagingTemplateS = '\
	<div class="pagination">\
		<a v-if="startIndex - 1 > 0 " href="#"  @click="pageChange(startIndex-indexViewSize)"> < </a>\
		<span class="number">\
			<template v-for="n in indexArray"> \
				<a href="#" v-if="index == n" class="active"  v-html="n + 1"></a>\
				<a href="#" v-if="index != n" @click="pageChange(n)" v-html="n+1"></a> \
			</template> \
		</span> \
		<a v-if="this.total/this.contentSize > (endIndex + 1)"  :class="{disabled : this.total/this.contentSize <= (endIndex + 1)}" @click="pageChange(startIndex + indexViewSize)"> > </a> \
	</div>';

// paging component
var pagingTemplate = {
	template: pagingTemplateS,
	props: {
		index: Number,
		total: Number,
		contentSize: Number,
	},
	data: function () {

	},
	computed: {
		startIndex: function () {
			var si = Math.floor(this.index / this.indexViewSize) * this.indexViewSize;
			return si;
		},
		endIndex: function () {

			var ei = this.startIndex + this.indexViewSize;
			var mi = Math.ceil(this.total / this.contentSize);

			if (ei >= mi) {
				ei = mi;
			}
			ei = ei - 1;
			if (ei < 0) {
				ei = 0;
			}

			return ei;
		},
		indexArray: function () {
			var indexArray = [];
			for (var i = this.startIndex; i <= this.endIndex; i++) {
				indexArray.push(i);
			}
			return indexArray;
		}
	},
	methods: {
		pageChange: function (value) {
			this.$emit("send-index", value);
		},
		nextPage: function (nextValue) {
			this.pageChange(nextValue);
		},
		prevPage: function (prevValue) {
			this.pageChange(prevValue);
		}
	}
};