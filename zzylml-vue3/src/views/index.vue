<template>
  <div class="app-container home">
    <!-- 主要横幅区域 -->
    <div class="hero-section">
      <div class="hero-content">
        <div class="hero-left">
          <h1 class="hero-title">
            <span class="gradient-text">梅林的中州养老</span>
            <br>
            <span class="hero-subtitle">智能管理系统</span>
          </h1>
          <p class="hero-description">
            专业的养老机构管理平台，集成护理管理、等级评估、计划制定等核心功能。
            <br>采用现代化架构设计，为养老服务提供全方位数字化解决方案。
          </p>
          
          <div class="feature-tags">
            <div class="feature-tag">
              <i class="el-icon-cpu"></i>
              <span>智能化</span>
            </div>
            <div class="feature-tag">
              <i class="el-icon-mobile-phone"></i>
              <span>移动端</span>
            </div>
            <div class="feature-tag">
              <i class="el-icon-unlock"></i>
              <span>开源免费</span>
            </div>
          </div>          <!-- GitHub 链接区域 -->
          <div class="github-section">
            <div class="github-card" @click="goTarget('https://github.com/SaintFore')">
              <div class="github-icon">
                <svg viewBox="0 0 24 24" width="28" height="28" fill="white">
                  <path d="M12,2A10,10 0 0,0 2,12C2,16.42 4.87,20.17 8.84,21.5C9.34,21.58 9.5,21.27 9.5,21C9.5,20.77 9.5,20.14 9.5,19.31C6.73,19.91 6.14,17.97 6.14,17.97C5.68,16.81 5.03,16.5 5.03,16.5C4.12,15.88 5.1,15.9 5.1,15.9C6.1,15.97 6.63,16.93 6.63,16.93C7.5,18.45 8.97,18 9.54,17.76C9.63,17.11 9.89,16.67 10.17,16.42C7.95,16.17 5.62,15.31 5.62,11.5C5.62,10.39 6,9.5 6.65,8.79C6.55,8.54 6.2,7.5 6.75,6.15C6.75,6.15 7.59,5.88 9.5,7.17C10.29,6.95 11.15,6.84 12,6.84C12.85,6.84 13.71,6.95 14.5,7.17C16.41,5.88 17.25,6.15 17.25,6.15C17.8,7.5 17.45,8.54 17.35,8.79C18,9.5 18.38,10.39 18.38,11.5C18.38,15.32 16.04,16.16 13.81,16.41C14.17,16.72 14.5,17.33 14.5,18.26C14.5,19.6 14.5,20.68 14.5,21C14.5,21.27 14.66,21.59 15.17,21.5C19.14,20.16 22,16.42 22,12A10,10 0 0,0 12,2Z" />
                </svg>
              </div>
              <div class="github-text">
                <div class="github-title">SaintFore's GitHub</div>
                <div class="github-subtitle">探索更多开源项目</div>
              </div>
              <div class="github-arrow">
                <i class="el-icon-right"></i>
              </div>
            </div>
          </div>
        </div>        <div class="hero-right">
          <div class="floating-card">
            <div class="status-indicator">
              <div class="status-dot"></div>
              <span>系统状态</span>
            </div>
            <div class="card-content">
              <div class="stat-item">
                <span class="stat-label">当前版本</span>
                <span class="stat-value">v{{ version }}</span>
              </div>
              <div class="stat-item">
                <span class="stat-label">在线状态</span>
                <span class="stat-value online">运行中</span>
              </div>
              <div class="stat-item">
                <span class="stat-label">技术栈</span>
                <span class="stat-value">Vue3 + Spring Boot</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>    <!-- 技术选型区域 -->
    <div class="tech-section">
      <div class="container">
        <div class="section-title">
          <h2>技术选型</h2>
          <p>采用现代化技术栈，确保系统的稳定性和可扩展性</p>
        </div>
        
        <div class="tech-categories">
          <!-- 后端技术 -->
          <div class="tech-category">
            <div class="category-header">
              <div class="category-icon backend">
                <i class="el-icon-cpu"></i>
              </div>
              <h3>后端技术</h3>
            </div>
            <div class="tech-grid">
              <div class="tech-card" v-for="tech in backendTechs" :key="tech.name">
                <div class="tech-header">
                  <div class="tech-icon">{{ tech.icon }}</div>
                  <div class="tech-info">
                    <h4 class="tech-name">{{ tech.name }}</h4>
                    <p class="tech-desc">{{ tech.desc }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 前端技术 -->
          <div class="tech-category">
            <div class="category-header">
              <div class="category-icon frontend">
                <i class="el-icon-monitor"></i>
              </div>
              <h3>前端技术</h3>
            </div>
            <div class="tech-grid">
              <div class="tech-card" v-for="tech in frontendTechs" :key="tech.name">
                <div class="tech-header">
                  <div class="tech-icon">{{ tech.icon }}</div>
                  <div class="tech-info">
                    <h4 class="tech-name">{{ tech.name }}</h4>
                    <p class="tech-desc">{{ tech.desc }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="Index">
import { ref } from 'vue'

const version = ref('3.8.9')

// 后端技术栈
const backendTechs = ref([
  {
    name: 'Spring Boot',
    desc: '强大的Java微服务框架',
    icon: '🍃'
  },
  {
    name: 'Spring Security',
    desc: '完善的安全认证框架',
    icon: '🔐'
  },
  {
    name: 'MyBatis Plus',
    desc: '高效的持久层框架',
    icon: '📊'
  },
  {
    name: 'MySQL',
    desc: '稳定可靠的数据库',
    icon: '🗄️'
  },
  {
    name: 'Redis',
    desc: '高性能缓存数据库',
    icon: '⚡'
  },
  {
    name: 'JWT',
    desc: '无状态身份验证',
    icon: '🎫'
  }
])

// 前端技术栈
const frontendTechs = ref([
  {
    name: 'Vue 3',
    desc: '渐进式JavaScript框架',
    icon: '💚'
  },
  {
    name: 'Element Plus',
    desc: '基于Vue3的组件库',
    icon: '🎨'
  },
  {
    name: 'Vite',
    desc: '下一代前端构建工具',
    icon: '⚡'
  },
  {
    name: 'Pinia',
    desc: 'Vue的状态管理库',
    icon: '🍍'
  },
  {
    name: 'Vue Router',
    desc: 'Vue官方路由管理器',
    icon: '🛣️'
  },
  {
    name: 'Axios',
    desc: '基于Promise的HTTP库',
    icon: '📡'
  }
])

function goTarget(url) {
  window.open(url, '_blank')
}
</script>

<style scoped lang="scss">
.home {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: 
      radial-gradient(circle at 20% 20%, rgba(120, 119, 198, 0.3) 0%, transparent 50%),
      radial-gradient(circle at 80% 80%, rgba(255, 119, 198, 0.3) 0%, transparent 50%),
      radial-gradient(circle at 40% 40%, rgba(120, 198, 121, 0.3) 0%, transparent 50%);
    pointer-events: none;
  }

  // 主要横幅区域
  .hero-section {
    position: relative;
    padding: 60px 20px;
    z-index: 1;
  }

  .hero-content {
    max-width: 1200px;
    margin: 0 auto;
    display: grid;
    grid-template-columns: 1fr 400px;
    gap: 60px;
    align-items: center;    @media (max-width: 768px) {
      grid-template-columns: 1fr;
      gap: 40px;
      text-align: center;
    }
  }

  // 标题样式
  .hero-title {
    font-size: 3.5rem;
    font-weight: 700;
    margin-bottom: 20px;
    line-height: 1.2;

    .gradient-text {
      background: linear-gradient(135deg, #ffffff 0%, #f0f8ff 100%);
      -webkit-background-clip: text;
      background-clip: text;
      -webkit-text-fill-color: transparent;
      text-shadow: 0 0 30px rgba(255, 255, 255, 0.5);
    }

    .hero-subtitle {
      font-size: 2rem;
      color: rgba(255, 255, 255, 0.9);
      font-weight: 400;
    }
  }

  .hero-description {
    font-size: 1.1rem;
    color: rgba(255, 255, 255, 0.8);
    line-height: 1.6;
    margin-bottom: 30px;
  }

  // 特性标签
  .feature-tags {
    display: flex;
    gap: 15px;
    margin-bottom: 30px;
    flex-wrap: wrap;

    .feature-tag {
      background: rgba(255, 255, 255, 0.2);
      backdrop-filter: blur(10px);
      border-radius: 25px;
      padding: 8px 16px;
      display: flex;
      align-items: center;
      gap: 8px;
      color: white;
      font-size: 0.9rem;
      transition: all 0.3s ease;

      &:hover {
        background: rgba(255, 255, 255, 0.3);
        transform: translateY(-2px);
      }

      i {
        font-size: 1.1rem;
      }
    }
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
  // 英雄区域样式
  .hero-section {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    position: relative;
    min-height: 80vh;
    display: flex;
    align-items: center;
    overflow: hidden;
    
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><circle cx="20" cy="20" r="2" fill="rgba(255,255,255,0.1)"/><circle cx="80" cy="30" r="1" fill="rgba(255,255,255,0.1)"/><circle cx="40" cy="70" r="1" fill="rgba(255,255,255,0.1)"/><circle cx="90" cy="80" r="2" fill="rgba(255,255,255,0.1)"/></svg>');
      animation: float 20s infinite linear;
    }
  }

  @keyframes float {
    0% { transform: translateY(0) rotate(0deg); }
    100% { transform: translateY(-100px) rotate(360deg); }
  }

  .hero-content {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 60px;
    align-items: center;
    position: relative;
    z-index: 1;
  }

  .hero-left {
    color: white;
  }
  .hero-title {
    font-size: 3.5rem;
    font-weight: 700;
    line-height: 1.2;
    margin-bottom: 24px;
    
    .gradient-text {
      background: linear-gradient(45deg, #fff, #e3f2fd);
      -webkit-background-clip: text;
      background-clip: text;
      -webkit-text-fill-color: transparent;
      text-shadow: 0 0 30px rgba(255, 255, 255, 0.3);
    }
    
    .hero-subtitle {
      color: rgba(255, 255, 255, 0.9);
      font-size: 2.8rem;
    }
  }

  .hero-description {
    font-size: 1.3rem;
    line-height: 1.8;
    color: rgba(255, 255, 255, 0.85);
    margin-bottom: 32px;
    max-width: 600px;
  }

  .feature-tags {
    display: flex;
    gap: 16px;
    margin-bottom: 40px;
    flex-wrap: wrap;
  }
  .feature-tag {
    background: rgba(255, 255, 255, 0.15);
    color: white;
    padding: 12px 24px;
    border-radius: 25px;
    font-size: 0.95rem;
    font-weight: 500;
    backdrop-filter: blur(10px);
    border: 1px solid rgba(255, 255, 255, 0.2);
    transition: all 0.3s ease;
    
    &:hover {
      background: rgba(255, 255, 255, 0.25);
      transform: translateY(-2px);
      box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
    }
  }
  .floating-card {
    position: absolute;
    top: 30px;
    right: 30px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 15px;
    padding: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(10px);
    animation: floatingCard 3s ease-in-out infinite;
      .status-indicator {
      display: flex;
      align-items: center;
      margin-bottom: 12px;
      
      .status-dot {
        width: 8px;
        height: 8px;
        background: #52c41a;
        border-radius: 50%;
        margin-right: 8px;
        animation: pulse 2s infinite;
      }
      
      span {
        color: #333;
        font-weight: 500;
        font-size: 0.9rem;
      }
    }
    
    .card-content {
      .stat-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 8px;
        
        &:last-child {
          margin-bottom: 0;
        }
        
        .stat-label {
          color: #666;
          font-size: 0.85rem;
        }
        
        .stat-value {
          color: #333;
          font-weight: 600;
          font-size: 0.9rem;
          
          &.online {
            color: #52c41a;
          }
        }
      }
    }
    
    .version {
      color: #666;
      font-size: 0.85rem;
    }
  }

  @keyframes floatingCard {
    0%, 100% { transform: translateY(0); }
    50% { transform: translateY(-10px); }
  }
  @keyframes pulse {
    0%, 100% { opacity: 1; }
    50% { opacity: 0.5; }
  }

  // 技术栈区域样式
  .tech-section {
    padding: 80px 0;
    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    position: relative;
    
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><polygon points="20,20 80,20 50,70" fill="rgba(102, 126, 234, 0.05)"/><circle cx="80" cy="80" r="10" fill="rgba(118, 75, 162, 0.05)"/></svg>');
      opacity: 0.5;
    }
    
    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 0 20px;
      position: relative;
      z-index: 1;
    }
    
    .section-title {
      text-align: center;
      font-size: 2.5rem;
      font-weight: 700;
      color: #333;
      margin-bottom: 60px;
      position: relative;
      
      &::after {
        content: '';
        position: absolute;
        bottom: -15px;
        left: 50%;
        transform: translateX(-50%);
        width: 80px;
        height: 4px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        border-radius: 2px;
      }
    }
  }

  .tech-category {
    margin-bottom: 50px;
    
    h3 {
      font-size: 1.5rem;
      color: #333;
      margin-bottom: 30px;
      font-weight: 600;
      display: flex;
      align-items: center;
      
      &::before {
        content: '';
        width: 4px;
        height: 24px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        border-radius: 2px;
        margin-right: 12px;
      }
    }
  }
  .tech-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 24px;
  }
  .tech-card {
    background: white;
    border-radius: 16px;
    padding: 24px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    transition: all 0.3s ease;
    border: 1px solid rgba(0, 0, 0, 0.06);
    
    &:hover {
      transform: translateY(-8px);
      box-shadow: 0 12px 40px rgba(0, 0, 0, 0.15);
      border-color: rgba(102, 126, 234, 0.2);
      
      .tech-icon {
        transform: scale(1.1) rotate(5deg);
      }
    }
    
    .tech-header {
      display: flex;
      align-items: center;
      margin-bottom: 16px;
    }
    
    .tech-icon {
      width: 48px;
      height: 48px;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 16px;
      font-size: 24px;
      transition: all 0.3s ease;
      
      &.spring { background: linear-gradient(135deg, #6DB33F, #4CAF50); }
      &.vue { background: linear-gradient(135deg, #4FC08D, #42B883); }
      &.mysql { background: linear-gradient(135deg, #4479A1, #00758F); }
      &.redis { background: linear-gradient(135deg, #DC382D, #B12A1C); }
      &.element { background: linear-gradient(135deg, #409EFF, #2196F3); }
      &.vite { background: linear-gradient(135deg, #646CFF, #747BFF); }
      &.security { background: linear-gradient(135deg, #FF6B6B, #EE5A24); }
      &.jwt { background: linear-gradient(135deg, #000000, #333333); }
      &.mybatis { background: linear-gradient(135deg, #E97627, #F39C12); }
      &.pinia { background: linear-gradient(135deg, #FFD43B, #FFC107); }
      &.router { background: linear-gradient(135deg, #42A5F5, #1E88E5); }
      &.axios { background: linear-gradient(135deg, #5A29E4, #7B68EE); }
    }
    
    .tech-name {
      font-size: 1.2rem;
      font-weight: 600;
      color: #333;
      margin: 0;
    }
    
    .tech-description {
      color: #666;
      font-size: 0.95rem;
      line-height: 1.6;
      margin: 0;
    }
  }

  // 响应式设计
  @media (max-width: 1024px) {
    .hero-content {
      grid-template-columns: 1fr;
      gap: 40px;
      text-align: center;
    }
    
    .hero-title {
      font-size: 3rem;
    }
  }

  @media (max-width: 768px) {
    .hero-title {
      font-size: 2.5rem;
    }
    
    .hero-description {
      font-size: 1.1rem;
    }
    
    .feature-tags {
      justify-content: center;
    }
    
    .floating-card {
      position: relative;
      top: auto;
      right: auto;
      margin: 20px auto;
      max-width: 280px;
    }
      .tech-grid {
      grid-template-columns: 1fr;
    }
    
    .section-title {
      font-size: 2rem;
    }
  }

  @media (max-width: 480px) {
    .hero-title {
      font-size: 2rem;
    }
    
    .hero-description {
      font-size: 1rem;
    }
    
    .github-card {
      padding: 24px;
    }
      .tech-card {
      padding: 20px;
    }
  }
}

  .version-tag {
    background: linear-gradient(45deg, #ff6b6b, #4ecdc4);
    color: white;
    padding: 2px 8px;
    border-radius: 12px;
    font-weight: bold;
    font-size: 12px;
  }

  .free-tag {
    animation: pulse 2s infinite;
  }

  .tech-tag {
    margin-left: 8px;
  }

  @keyframes pulse {
    0% {
      transform: scale(1);
    }
    50% {
      transform: scale(1.05);
    }
    100% {
      transform: scale(1);
    }
  }

  .github-section {
    margin: 25px 0;
  }

  .github-card {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 16px;
    padding: 20px;
    color: white;
    cursor: pointer;
    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    display: flex;
    align-items: center;
    gap: 15px;
    position: relative;
    overflow: hidden;
    box-shadow: 0 10px 30px rgba(102, 126, 234, 0.3);

    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: -100%;
      width: 100%;
      height: 100%;
      background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
      transition: left 0.5s;
    }

    &:hover {
      transform: translateY(-5px) scale(1.02);
      box-shadow: 0 20px 40px rgba(102, 126, 234, 0.4);

      &::before {
        left: 100%;
      }

      .github-arrow {
        transform: translateX(5px);
      }

      .github-icon {
        transform: rotate(360deg);
      }
    }

    .github-icon {
      width: 50px;
      height: 50px;
      background: rgba(255, 255, 255, 0.2);
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: transform 0.6s ease;

      svg {
        width: 28px;
        height: 28px;
      }
    }

    .github-text {
      flex: 1;

      .github-title {
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 4px;
      }

      .github-subtitle {
        font-size: 14px;
        opacity: 0.9;
      }
    }

    .github-arrow {
      font-size: 24px;
      font-weight: bold;
      transition: transform 0.3s ease;
    }
  }

  .button-group {
    margin-top: 20px;

    .el-button {
      margin-right: 10px;
      margin-bottom: 10px;
    }
  }

  .github-link {
    font-weight: bold;
    text-decoration: none;
    
    &:hover {
      text-decoration: underline;
    }
  }
</style>

