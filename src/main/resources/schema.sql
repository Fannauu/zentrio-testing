-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- USERS
CREATE TABLE users (
                       user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       username VARCHAR(50) NOT NULL,
                       gender VARCHAR(20),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR NOT NULL,
                       profile_image VARCHAR DEFAULT 'https://i.pinimg.com/736x/d0/7b/a6/d07ba6dcf05fa86c0a61855bc722cb7a.jpg',
                       is_verified BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP DEFAULT now()
);

-- ROLES
CREATE TABLE roles (
                       role_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       role_name VARCHAR(50) NOT NULL UNIQUE
);

-- USER_ROLES (junction)
CREATE TABLE user_roles (
                            user_role_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                            role_id UUID NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE
);

-- GANTT_CHARTS
CREATE TABLE gantt_charts (
                              gantt_chart_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              title VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP DEFAULT now(),
                              updated_at TIMESTAMP DEFAULT now()
);

-- WORKSPACES
CREATE TABLE workspaces (
                            workspace_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            title VARCHAR(255) NOT NULL,
                            description TEXT,
                            created_by UUID REFERENCES users(user_id) ON DELETE SET NULL,
                            created_at TIMESTAMP DEFAULT now(),
                            updated_at TIMESTAMP DEFAULT now()
);

-- BOARDS
CREATE TABLE boards (
                        board_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        is_verified BOOLEAN DEFAULT FALSE,
                        gantt_chart_id UUID REFERENCES gantt_charts(gantt_chart_id) ON DELETE SET NULL,
                        workspace_id UUID REFERENCES workspaces(workspace_id) ON DELETE CASCADE,
                        created_at TIMESTAMP DEFAULT now(),
                        updated_at TIMESTAMP DEFAULT now()
);

-- MEMBERS
CREATE TABLE members (
                         member_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                         role_id UUID NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE,
                         board_id UUID NOT NULL REFERENCES boards(board_id) ON DELETE CASCADE
);

-- GANTT_BARS
CREATE TABLE gantt_bars (
                            gantt_bar_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            title VARCHAR(255) NOT NULL,
                            started_at TIMESTAMP NOT NULL,
                            finished_at TIMESTAMP,
                            gantt_chart_id UUID REFERENCES gantt_charts(gantt_chart_id) ON DELETE CASCADE
);

-- TASKS
CREATE TABLE tasks (
                       task_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       stage VARCHAR(50),
                       is_done BOOLEAN DEFAULT FALSE,
                       board_id UUID REFERENCES boards(board_id) ON DELETE CASCADE,
                       gantt_bar_id UUID REFERENCES gantt_bars(gantt_bar_id) ON DELETE SET NULL,
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP DEFAULT now(),
                       finished_at TIMESTAMP
);

-- TASK_ASSIGNMENT
CREATE TABLE task_assignment (
                                 task_assign_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 assigned_to UUID REFERENCES members(member_id) ON DELETE CASCADE,
                                 assigned_by UUID REFERENCES users(user_id) ON DELETE SET NULL,
                                 task_id UUID REFERENCES tasks(task_id) ON DELETE CASCADE
);

-- CHECKLISTS
CREATE TABLE checklists (
                            checklist_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            title VARCHAR(255) NOT NULL,
                            is_done BOOLEAN DEFAULT FALSE,
                            task_id UUID REFERENCES tasks(task_id) ON DELETE CASCADE,
                            created_at TIMESTAMP DEFAULT now(),
                            updated_at TIMESTAMP DEFAULT now(),
                            finished_at TIMESTAMP
);

-- CHECKLIST_ITEMS
CREATE TABLE checklist_items (
                                 checklist_item_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 title VARCHAR(255) NOT NULL,
                                 status VARCHAR(50),
                                 started_at TIMESTAMP,
                                 checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE
);

-- CHECKLIST_ASSIGNMENTS
CREATE TABLE checklist_assignments (
                                       checklist_assign_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                       member_id UUID REFERENCES members(member_id) ON DELETE CASCADE,
                                       assigned_by UUID REFERENCES users(user_id) ON DELETE SET NULL,
                                       checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE
);

-- LABELS
CREATE TABLE labels (
                        label_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        label_title VARCHAR(50) NOT NULL,
                        label_color VARCHAR(20),
                        checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE
);

-- CALENDARS
CREATE TABLE calendars (
                           calendar_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           noted TEXT,
                           user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
                           checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE
);

-- COMMENTS
CREATE TABLE comments (
                          comment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          content TEXT NOT NULL,
                          comment_date TIMESTAMP DEFAULT now(),
                          checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE,
                          commented_by UUID REFERENCES checklist_assignments(checklist_assign_id) ON DELETE SET NULL
);

-- DOCUMENTS
CREATE TABLE documents (
                           document_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           doc_type VARCHAR(50),
                           is_private BOOLEAN DEFAULT FALSE,
                           user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
                           board_id UUID REFERENCES boards(board_id) ON DELETE CASCADE,
                           created_at TIMESTAMP DEFAULT now()
);

-- ATTACHMENT
CREATE TABLE attachment (
                            attachment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            details JSONB,
                            checklist_id UUID REFERENCES checklists(checklist_id) ON DELETE CASCADE,
                            created_at TIMESTAMP DEFAULT now(),
                            updated_at TIMESTAMP DEFAULT now()
);

-- ACHIEVEMENTS
CREATE TABLE achievement (
                             achievement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                             details JSONB,
                             user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
                             created_at TIMESTAMP DEFAULT now(),
                             updated_at TIMESTAMP DEFAULT now()
);

-- NOTIFICATIONS
CREATE TABLE notifications (
                               notification_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                               content TEXT,
                               type VARCHAR(50),
                               is_read BOOLEAN DEFAULT FALSE,
                               task_assignment UUID REFERENCES task_assignment(task_assign_id) ON DELETE CASCADE,
                               user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
                               created_at TIMESTAMP DEFAULT now()
);

-- REPORT
CREATE TABLE report (
                        report_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        details JSONB,
                        board_id UUID REFERENCES boards(board_id) ON DELETE CASCADE,
                        created_at TIMESTAMP DEFAULT now(),
                        updated_at TIMESTAMP DEFAULT now()
);

-- FEEDBACK
CREATE TABLE feedback (
                          feedback_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          comment TEXT,
                          task_id UUID REFERENCES tasks(task_id) ON DELETE CASCADE,
                          feedback_by UUID REFERENCES users(user_id) ON DELETE SET NULL,
                          created_at TIMESTAMP DEFAULT now()
);

-- Seed initial roles
INSERT INTO roles (role_name)
VALUES ('ROLE_MANAGER'), ('ROLE_LEADER'), ('ROLE_MEMBER');
