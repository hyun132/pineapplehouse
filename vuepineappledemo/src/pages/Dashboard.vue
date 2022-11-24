<template>
  <div class="containerBody">
    <div class="img">
      <img class="pine" src="@/assets/img/pineapple.png" />
      <div class="title">파인애플 하우스</div>
      <img class="pine" src="@/assets/img/pineapple.png" />
    </div>
    <div class="border-bottom"></div>
    <section id="estate" class="section">
      <div class="estate">
        <a href="http://localhost:8080/#/news">
          <div class="estate__card apt">
            <img class="estate__icon" src="@/assets/img/newspaper.png" alt="" />
            <h2 class="estate__title _1">뉴스</h2>
            <p class="estate__text">
              <strong>주택 관련 뉴스가 </strong><br />
              <strong>궁금하다면 여기로 오세요!</strong>
            </p>
          </div>
        </a>
        <a href="http://localhost:8080/#/news">
          <div class="estate__card apt2">
            <img class="estate__icon" src="@/assets/img/home.png" alt="" />
            <h2 class="estate__title _2">실거래 정보</h2>
            <p class="estate__text">
              <strong>내게 꼭 맞는</strong> <br />
              <strong>집을 구해보세요!</strong>
            </p>
          </div>
        </a>
        <a href="http://localhost:8080/#/board/list">
          <div class="estate__card land">
            <img class="estate__icon" src="@/assets/img/coworking.png" alt="" />
            <h2 class="estate__title _3">게시판</h2>
            <p class="estate__text">
              <strong>다른 사람들과 의견을</strong> <br />
              <strong>나누고 싶을땐 여기로 오세요!</strong>
            </p>
          </div>
        </a>
      </div>
    </section>
  </div>
</template>

<script>
export default {};
</script>

<style>
.img {
  background-size: 100%;
  width: 100%;
  height: 300px;
  text-shadow: white 0px 0px 2px;
  font-size: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pine {
  width: 80px;
  height: 80px;
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
}

.title {
  color: black;
  font-size: 60px;
  font-weight: bold;
  text-shadow: #66615b 0px 0px 10px;
}

.section {
  padding: 64px 16px;
  text-align: center;
}

.section__tag {
  font-size: var(--font-size-tag);
  color: var(--color-sub);
}

.section__title {
  font-size: var(--font-size-title);
  font-weight: bold;
  color: var(--color-text);
  margin-bottom: 2rem;
}

.section__main > table {
  margin: auto;
}

#ad {
  background-color: var(--color-lighter);
  height: 40vh;
  text-align: center;
}

#ad h1 {
  font-size: 3rem;
}

@media (min-width: 769px) {
  .index__main {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
}
#estate {
  background-color: var(--color-light-bg);
}

.estate {
  display: inline-flex;
  gap: 1em;
  margin-top: 48px;
}

.estate__card {
  padding: 48px;
  text-align: center;
  color: black;
  background-color: var(--color-dark);
  border-radius: 12px;
}
.estate__icon {
  width: 120px;
  opacity: 0.25;
}

.estate__title {
  margin-top: 0.6em;
  font-size: 20px;
  font-weight: bold;
}

.estate__title._1 {
  color: black;
}
.estate__title._2 {
  color: black;
}
.estate__title._3 {
  color: black;
}

.about__text {
  margin-top: 0.8em;
  font-size: var(--font-size-text);
}

@media (max-width: 768px) {
  #estate {
    margin-top: 420px;
  }
  .estate {
    flex-direction: column;
    width: 100%;
  }
}

@media (min-width: 769px) {
  #estate {
    grid-column: 1 / -1;
  }

  .estate__card {
    width: 320px;
    cursor: pointer;
    transition: all 350ms;
    background-color: #a8a198;
  }

  .estate:hover .estate__card:not(:hover) {
    transform: scale(0.9);
  }

  /* 흐림 필터링을 위한 덮개 */
  .estate:hover .estate__card:not(:hover)::after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    -webkit-backdrop-filter: blur(2px);
    backdrop-filter: blur(2px);
  }

  .estate__card:hover {
    transform: scale(1.04);
  }
  .estate__card:hover .estate__icon {
    opacity: 3;
  }
}
</style>
